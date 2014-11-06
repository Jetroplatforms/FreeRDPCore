package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TicketValidationMsg extends BaseMsg {
	
	@Expose @SerializedName("Ticket")
	private String ticket;
	
	private TicketValidationMsgResponse resposne;
	
	public TicketValidationMsg() {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.TicketValidationMsg;
	}
	
	public TicketValidationMsg(String ticket) {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.TicketValidationMsg;
		this.ticket = ticket;
	}

	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, TicketValidationMsgResponse.class);
		} catch (Exception e) {
			resposne = new TicketValidationMsgResponse();
		}
	}	
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}	
	
	public static class TicketValidationMsgResponse extends BaseResponse {

		public TicketValidationMsgResponse() {
		}
		
	}
	
}
