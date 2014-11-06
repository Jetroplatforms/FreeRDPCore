package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SessionEndMsg extends BaseMsg {
	
	@Expose @SerializedName("Wait")
	boolean wait = true;
	
	private SessionEndMsgResponse resposne;

	public SessionEndMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.SessionEndMsg;
	}
	
	public SessionEndMsg(boolean wait) {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.SessionEndMsg;
		this.wait = wait;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, SessionEndMsgResponse.class);
		} catch (Exception e) {
			resposne = new SessionEndMsgResponse();
		}

	}

	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}

	public static class SessionEndMsgResponse extends BaseResponse {
		
		boolean Wait;

		public SessionEndMsgResponse() {
		}

		public boolean isWait() {
			return Wait;
		}
	}	

}
