package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;

public class WindowChangeMsg extends BaseMsg {
	
	private WindowChangeMsgResponse resposne;	
	
	public WindowChangeMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.WindowChangeMsg;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, WindowChangeMsgResponse.class);
		} catch (Exception e) {
			resposne = new WindowChangeMsgResponse();
		}
	}
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class WindowChangeMsgResponse extends BaseResponse {
		
		int HWND;
		String Title;
		
		public WindowChangeMsgResponse() {
		}
		
		public String getTitle() {
			return Title;
		}

		public int getHWND() {
			return HWND;
		}	
	}		

}
