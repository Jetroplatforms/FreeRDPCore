package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;

public class ShowKeyBoardMsg extends BaseMsg {
	
	private ShowKeyBoardMsgResponse resposne;
	
	public ShowKeyBoardMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.ShowKeyBoardMsg;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, ShowKeyBoardMsgResponse.class);
		} catch (Exception e) {
			resposne = new ShowKeyBoardMsgResponse();
		}
	}
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class ShowKeyBoardMsgResponse extends BaseResponse {
		
		boolean Show;
		
		public ShowKeyBoardMsgResponse() {
		}

		public boolean isShow() {
			return Show;
		}
	}	
}
