package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.application.GlobalApp;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginMsg extends BaseMsg {

	@Expose @SerializedName("Name")
	String name;
	@Expose @SerializedName("Password")
	String password;
	@Expose @SerializedName("Domain")
	String domain;
	@Expose @SerializedName("DeviceModel")
	String deviceModel;
	@Expose @SerializedName("DeviceID")
	String deviceId;
	
	private LoginMsgResponse response;
	
	public LoginMsg() {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.LoginMsg;
	}

	public LoginMsg(String name, String password, String domain,
			String deviceModel, String deviceId) {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.LoginMsg;
		this.name = name;
		this.password = password;
		this.deviceId = deviceId;
		this.domain = domain;
		this.deviceModel = deviceModel;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			response = g.fromJson(s, LoginMsgResponse.class);
			
			// if login was successful - save the session ticket in globalApp
			// we later on use it to perform pings from time to time
			// as per the documentation
			// TODO: check if needed
			if (response.getReturnCode() != MessagesValues.LoginReturnCode.Error) {
				GlobalApp.setSessionTicket(response.getTicket());
			}
		} catch (Exception e) {
			response = new LoginMsgResponse();
		}
	}

	@Override
	public BaseResponse getJsonResponse() {
		return response;
	}

	public static class LoginMsgResponse extends BaseResponse {
		
		int ReturnCode;
		int DaysBeforePasswordExpiration;
		
		public LoginMsgResponse() {
		}
		
		public int getReturnCode() {
			return ReturnCode;
		}

		public int getDaysBeforePasswordExpiration() {
			return DaysBeforePasswordExpiration;
		}
	}
}
