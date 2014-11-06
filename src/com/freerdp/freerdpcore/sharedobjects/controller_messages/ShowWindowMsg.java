package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ShowWindowMsg extends BaseMsg {
	
	@Expose @SerializedName("PID")
	int pId;
	@Expose @SerializedName("HWND")
	int hwnd;
	
	private ShowWindowMsgResponse resposne;

	public ShowWindowMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.ShowWindowMsg;
	}
	
	public ShowWindowMsg(int pId, int hwnd) {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.ShowWindowMsg;
		this.pId = pId;
		this.hwnd = hwnd;
	}

	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, ShowWindowMsgResponse.class);
		} catch (Exception e) {
			resposne = new ShowWindowMsgResponse();
		}
	}	
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class ShowWindowMsgResponse extends BaseResponse {
		
		int PID;
		int HWND;
		
		public ShowWindowMsgResponse() {
		}
		
		public int getPID() {
			return PID;
		}

		public int getHWND() {
			return HWND;
		}		
	}	
	
}
