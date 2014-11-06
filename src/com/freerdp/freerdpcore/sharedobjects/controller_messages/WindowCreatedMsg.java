package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.Task;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;

public class WindowCreatedMsg extends BaseMsg {
	
	private WindowCreatedMsgResponse resposne;
	
	public WindowCreatedMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.WindowCreatedMsg;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, WindowCreatedMsgResponse.class);
		} catch (Exception e) {
			resposne = new WindowCreatedMsgResponse();
		}
	}
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class WindowCreatedMsgResponse extends BaseResponse {
		
		Task Task;

		public Task getTask() {
			return Task;
		}
	}	
}
