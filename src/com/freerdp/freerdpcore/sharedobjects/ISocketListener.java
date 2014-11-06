package com.freerdp.freerdpcore.sharedobjects;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;

public interface ISocketListener {
	/**
	 * called when the socket is opened and connected to server
	 */
	void OnSocketCreated();

	/**
	 * called when finishing processing the message from the server
	 * 
	 * @param msg
	 *            - sub classes of the parsed message response
	 */
	void OnMessageReceived(BaseMsg msg);

	/**
	 * notify when there is an IO error (lost connection, stream read/write
	 * problem)
	 */
	void OnIOError(String exception);
}
