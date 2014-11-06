package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;

public class SessionReadyMsg extends BaseMsg {
	
	private SessionReadyMsgResponse resposne;
	
	public SessionReadyMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.SessionReadyMsg;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, SessionReadyMsgResponse.class);
		} catch (Exception e) {
			resposne = new SessionReadyMsgResponse();
		}
	}
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class SessionReadyMsgResponse extends BaseResponse {
		
		int SessionID;
		String CockpitSessionID;
		String Username;
		String DomainName;
		
		public SessionReadyMsgResponse() {
		}
		
		public int getSessionID() {
			return SessionID;
		}
		
		public String getCockpitSessionID() {
			return CockpitSessionID;
		}

		public String getUsername() {
			return Username;
		}

		public String getDomainName() {
			return DomainName;
		}
		
	}
}
