package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;

public class WindowDestroyedMsg extends BaseMsg {
	
	private WindowDestroyedMsgResponse resposne;
	
	public WindowDestroyedMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.WindowDestroyedMsg;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, WindowDestroyedMsgResponse.class);
		} catch (Exception e) {
			resposne = new WindowDestroyedMsgResponse();
		}
	}
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class WindowDestroyedMsgResponse extends BaseResponse {
		
		String AppID;
		int PID;
		int HWND;
		
		public WindowDestroyedMsgResponse() {
		}
		
		public String getAppID() {
			return AppID;
		}		
		
		public int getPID() {
			return PID;
		}

		public int getHWND() {
			return HWND;
		}		
	}	
}
