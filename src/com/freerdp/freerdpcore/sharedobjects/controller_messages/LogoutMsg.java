package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LogoutMsg extends BaseMsg {

	@Expose @SerializedName("Ticket")
	String ticket;
	
	private LogoutMsgResponse response;
	
	public LogoutMsg() {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.LogoutMsg;
	}

	public LogoutMsg(String ticket) {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.LogoutMsg;
		this.ticket = ticket;
	}

	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			response = g.fromJson(s, LogoutMsgResponse.class);
		} catch (Exception e) {
			response = new LogoutMsgResponse();
		}
	}

	@Override
	public BaseResponse getJsonResponse() {
		return response;
	}

	public class LogoutMsgResponse extends BaseResponse {

		public LogoutMsgResponse() {
		}

	}
}
