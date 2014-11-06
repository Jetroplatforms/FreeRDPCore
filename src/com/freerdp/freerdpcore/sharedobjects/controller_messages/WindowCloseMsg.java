package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WindowCloseMsg extends BaseMsg  {
	
	@Expose @SerializedName("HWND")
	int HWND;
	
	private WindowCloseMsgResponse resposne;
	
	public WindowCloseMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.WindowCloseMsg;
	}
	
	public WindowCloseMsg(int HWND) {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.WindowCloseMsg;
		this.HWND = HWND;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, WindowCloseMsgResponse.class);
		} catch (Exception e) {
			resposne = new WindowCloseMsgResponse();
		}
	}
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class WindowCloseMsgResponse extends BaseResponse {
		
		int HWND;
		
		public WindowCloseMsgResponse() {
		}
		
		public int getHWND() {
			return HWND;
		}		
	}	
}
