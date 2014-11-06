package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.ConnectionPoint;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CockpitSiteInfoMsg extends BaseMsg {

	@Expose @SerializedName("RX")
	int resolutionX;
	@Expose @SerializedName("RY")
	int resolutionY;
	
	private CPTInfoResponse response;

	public CockpitSiteInfoMsg() {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.CockpitSiteInfoMsg;
	}

	public CockpitSiteInfoMsg(int rX, int rY) {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.CockpitSiteInfoMsg;
		this.resolutionX = rX;
		this.resolutionY = rY;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			response = g.fromJson(s, CPTInfoResponse.class);
		} catch (Exception e) {
			response = new CPTInfoResponse();
		}
	}

	@Override
	public BaseResponse getJsonResponse() {
		return response;
	}

	public static class CPTInfoResponse extends BaseResponse {

		String RX;
		String RY;
		String ID;
		String Name;
		ConnectionPoint[] ConnectionPoints;
		String LoginScreenText;
		String LoginScreenImage;

		public String getRX() {
			return RX;
		}

		public String getRY() {
			return RY;
		}

		public String getID() {
			return ID;
		}

		public String getName() {
			return Name;
		}

		public ConnectionPoint[] getConnectionPoints() {
			return ConnectionPoints;
		}

		public String getLoginScreenText() {
			return LoginScreenText;
		}

		public String getLoginScreenImage() {
			return LoginScreenImage;
		}
	}
}
