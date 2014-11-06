package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartApplicationMsg extends BaseMsg {
	
	@Expose @SerializedName("ID")
	private String id;
	
	private StartApplicationMsgResponse resposne;	
	
	public StartApplicationMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.StartApplicationMsg;
	}
	
	public StartApplicationMsg(String id) {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.StartApplicationMsg;
		this.id = id;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, StartApplicationMsgResponse.class);
		} catch (Exception e) {
			resposne = new StartApplicationMsgResponse();
		}
	}
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class StartApplicationMsgResponse extends BaseResponse {
		
		String ID;
		int PID;
		
		public StartApplicationMsgResponse() {
		}

		public String getID() {
			return ID;
		}
		
		public int getPID() {
			return PID;
		}		
	}		

}
