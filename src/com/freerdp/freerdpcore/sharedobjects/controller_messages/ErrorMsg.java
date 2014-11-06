package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;

public class ErrorMsg extends BaseMsg  {
	
	private ErrCodeMsgResponse resposne;
	
	public ErrorMsg() {
		msgCategory = MessagesValues.GENERIC;
		msgCalssID = ClassID.Error;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, ErrCodeMsgResponse.class);
		} catch (Exception e) {
			resposne = new ErrCodeMsgResponse();
		}
	}
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class ErrCodeMsgResponse extends BaseResponse {
		
		public ErrCodeMsgResponse() {
		}

	}	
}
