package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.application.GlobalApp;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPasswordMsg extends BaseMsg {
	
	@Expose @SerializedName("Name")
	String name;
	@Expose @SerializedName("NewPassword")
	String newPassword;
	@Expose @SerializedName("OldPassword")
	String oldPassword;
	@Expose @SerializedName("Domain")
	String domain;

	private ResetPasswordMsgResponse response;
	
	public ResetPasswordMsg() {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.ResetPasswordMsg;
	}

	public ResetPasswordMsg(String name, String newPassword, String oldPassword, String domain) {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.ResetPasswordMsg;
		this.name = name;
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
		this.domain = domain;
	}

	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			response = g.fromJson(s, ResetPasswordMsgResponse.class);
			
			// reset password successful - save the ticket
			// TODO: check if needed here
			GlobalApp.setSessionTicket(response.getTicket());
		} catch (Exception e) {
			response = new ResetPasswordMsgResponse();
		}
	}

	@Override
	public BaseResponse getJsonResponse() {
		return response;
	}

	public static class ResetPasswordMsgResponse extends BaseResponse {
		
		public ResetPasswordMsgResponse() {
		}
		
	}	
}
