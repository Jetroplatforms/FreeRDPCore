package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.Application;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MyApplicationsMsg extends BaseMsg {

	@Expose @SerializedName("Ticket")
	String ticket;
	
	private MyAppsResponse resposne;
	
	public MyApplicationsMsg() {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.MyApplicationsMsg;
	}

	public MyApplicationsMsg(String ticket) {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.MyApplicationsMsg;
		this.ticket = ticket;
	}

	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, MyAppsResponse.class);
		} catch (Exception e) {
			resposne = new MyAppsResponse();
		}
	}

	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}

	public static class MyAppsResponse extends BaseResponse {

		Application[] Applications;
		
		public MyAppsResponse() {
		}

		public Application[] getApplications() {
			return Applications;
		}
	}
}
