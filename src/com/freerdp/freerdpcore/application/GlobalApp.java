/*
   Android Main Application

   Copyright 2013 Thincast Technologies GmbH, Author: Martin Fleisz

   This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. 
   If a copy of the MPL was not distributed with this file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.freerdp.freerdpcore.application;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Application;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import com.freerdp.freerdpcore.domain.BookmarkBase;
import com.freerdp.freerdpcore.services.BookmarkDB;
import com.freerdp.freerdpcore.services.HistoryDB;
import com.freerdp.freerdpcore.services.LibFreeRDP;
import com.freerdp.freerdpcore.services.ManualBookmarkGateway;
import com.freerdp.freerdpcore.services.QuickConnectHistoryGateway;
import com.freerdp.freerdpcore.sharedobjects.ConnectionPoint;
import com.freerdp.freerdpcore.sharedobjects.ISocketListener;
import com.freerdp.freerdpcore.sharedobjects.SocketManager;

public class GlobalApp extends Application implements LibFreeRDP.EventListener {
	private static Map<Integer, SessionState> sessionMap;

	public static boolean ConnectedTo3G = false;

	// event notification defines
	public static final String EVENT_TYPE = "EVENT_TYPE";
	public static final String EVENT_PARAM = "EVENT_PARAM";
	public static final String EVENT_STATUS = "EVENT_STATUS";
	public static final String EVENT_ERROR = "EVENT_ERROR";

	public static final String ACTION_EVENT_FREERDP = "com.freerdp.freerdp.event.freerdp";

	public static final int FREERDP_EVENT_CONNECTION_SUCCESS = 1;
	public static final int FREERDP_EVENT_CONNECTION_FAILURE = 2;
	public static final int FREERDP_EVENT_DISCONNECTED = 3;

	private static BookmarkDB bookmarkDB;
	private static ManualBookmarkGateway manualBookmarkGateway;

	private static HistoryDB historyDB;
	private static QuickConnectHistoryGateway quickConnectHistoryGateway;

	// timer for disconnecting sessions after the screen was turned off
	private static Timer disconnectTimer = null;
	
	// our current active connection point
	private static ConnectionPoint connectionPoint;
	
	private static SocketManager socketManager;
	
	private static String SessionTicket = "";
	
	/**
	 * get socket manager singleton instance
	 * 
	 * @param listener
	 *            - socket listener for events triggering
	 * @return - the singleton instance
	 */
	public static SocketManager getSocketManager(ISocketListener listener) {
		return SocketManager.getInstance(listener);
	}	
	
	/**
	 * set the current active connection point
	 * 
	 */
	public static void SetConnectionPoint(ConnectionPoint connectionPoint) {
		GlobalApp.connectionPoint = connectionPoint;
	}
	
	/**
	 * get the current active connection point
	 * 
	 */	
	public static ConnectionPoint GetConnectionPoint() {
		return GlobalApp.connectionPoint;
	}
	
		

	// TimerTask for disconnecting sessions after screen was turned off
	private static class DisconnectTask extends TimerTask {
		@Override
		public void run() {
			Log.v("DisconnectTask", "Doing action");

			// disconnect any running rdp session
			Collection<SessionState> sessions = GlobalApp.getSessions();
			for (SessionState session : sessions) {
				LibFreeRDP.disconnect(session.getInstance());
			}
		}
	}

	public GlobalApp() {
		sessionMap = Collections
				.synchronizedMap(new HashMap<Integer, SessionState>());

		GlobalApp.load();
		LibFreeRDP.setEventListener(this);
	}

	@Override
	public void onCreate() {
		super.onCreate();

		bookmarkDB = new BookmarkDB(this);

		manualBookmarkGateway = new ManualBookmarkGateway(bookmarkDB);

		historyDB = new HistoryDB(this);
		quickConnectHistoryGateway = new QuickConnectHistoryGateway(historyDB);

		GlobalSettings.init(this);
		ConnectedTo3G = NetworkStateReceiver.isConnectedTo3G(this);

		// init screen receiver here (this can't be declared in AndroidManifest
		// - refer to:
		// http://thinkandroid.wordpress.com/2010/01/24/handling-screen-off-and-screen-on-intents/
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_ON);
		filter.addAction(Intent.ACTION_SCREEN_OFF);
		registerReceiver(new ScreenReceiver(), filter);
	}

	public static ManualBookmarkGateway getManualBookmarkGateway() {
		return manualBookmarkGateway;
	}

	public static QuickConnectHistoryGateway getQuickConnectHistoryGateway() {
		return quickConnectHistoryGateway;
	}

	static private void load() {
		final String libname = "freerdp-android";
		final String LD_PATH = System.getProperty("java.library.path");

		Log.v("LibFreeRDP", "Trying to load library " + libname
				+ " from LD_PATH: " + LD_PATH);

		try {
			System.loadLibrary(libname);
		} catch (UnsatisfiedLinkError e) {
			Log.e("LibFreeRDP", e.toString());
		}
	}

	// Disconnect handling for Screen on/off events
	static public void startDisconnectTimer() {
		int timeoutMinutes = GlobalSettings.getDisconnectTimeout();
		if (timeoutMinutes > 0) {
			// start disconnect timeout...
			disconnectTimer = new Timer();
			disconnectTimer.schedule(new DisconnectTask(),
					timeoutMinutes * 60 * 1000);
		}
	}

	static public void cancelDisconnectTimer() {
		// cancel any pending timer events
		if (disconnectTimer != null) {
			disconnectTimer.cancel();
			disconnectTimer.purge();
			disconnectTimer = null;
		}
	}

	// RDP session handling
	static public SessionState createSession(BookmarkBase bookmark) {
		SessionState session = new SessionState(LibFreeRDP.newInstance(),
				bookmark);
		sessionMap.put(Integer.valueOf(session.getInstance()), session);
		return session;
	}

	static public SessionState getSession(int instance) {
		return sessionMap.get(instance);
	}

	static public Collection<SessionState> getSessions() {
		// return a copy of the session items
		return new ArrayList<SessionState>(sessionMap.values());
	}

	static public void freeSession(int instance) {
		if (GlobalApp.sessionMap.containsKey(instance)) {
			GlobalApp.sessionMap.remove(instance);
			LibFreeRDP.freeInstance(instance);
		}
	}

	// helper to send FreeRDP notifications
	private void sendRDPNotification(int type, int param) {
		// send broadcast
		Intent intent = new Intent(ACTION_EVENT_FREERDP);
		intent.putExtra(EVENT_TYPE, type);
		intent.putExtra(EVENT_PARAM, param);
		sendBroadcast(intent);
	}

	// //////////////////////////////////////////////////////////////////////
	// Implementation of LibFreeRDP.EventListener
	public void OnConnectionSuccess(int instance) {
		Log.v("LibFreeRDP", "OnConnectionSuccess");
		sendRDPNotification(FREERDP_EVENT_CONNECTION_SUCCESS, instance);
	}

	public void OnConnectionFailure(int instance) {
		Log.v("LibFreeRDP", "OnConnectionFailure");

		// send notification to session activity
		sendRDPNotification(FREERDP_EVENT_CONNECTION_FAILURE, instance);
	}

	public void OnDisconnecting(int instance) {
		Log.v("LibFreeRDP", "OnDisconnecting");

		// send disconnect notification
		sendRDPNotification(FREERDP_EVENT_DISCONNECTED, instance);
	}

	public void OnDisconnected(int instance) {
		Log.v("LibFreeRDP", "OnDisconnected");
	}

	public static String getSessionTicket() {
		return SessionTicket;
	}

	public static void setSessionTicket(String sessionTicket) {
		SessionTicket = sessionTicket;
	}
}
