package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.Task;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;

public class ShowTaskListMsg extends BaseMsg {
	
	private ShowTaskListMsgResponse resposne;
	
	public ShowTaskListMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.ShowTaskListMsg;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, ShowTaskListMsgResponse.class);
		} catch (Exception e) {
			resposne = new ShowTaskListMsgResponse();
		}
	}
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class ShowTaskListMsgResponse extends BaseResponse {
		
		int ActiveHWND;
		Task[] Tasks;
		
		public ShowTaskListMsgResponse() {
		}

		public int getActiveHWND() {
			return ActiveHWND;
		}

		public Task[] getTasks() {
			return Tasks;
		}
	}
}
