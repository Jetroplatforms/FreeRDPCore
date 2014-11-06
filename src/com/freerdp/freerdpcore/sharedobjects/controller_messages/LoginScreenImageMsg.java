package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginScreenImageMsg extends BaseMsg {

	@Expose @SerializedName("ImageName")
	String imageName;
	
	private ScreenImageResponse response;

	public LoginScreenImageMsg() {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.LoginScreenImageMsg;
	}

	public LoginScreenImageMsg(String imageName) {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.LoginScreenImageMsg;
		this.imageName = imageName;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			response = g.fromJson(s, ScreenImageResponse.class);
		} catch (Exception e) {
			response = new ScreenImageResponse();
		}
	}

	@Override
	public BaseResponse getJsonResponse() {
		return response;
	}

	public static class ScreenImageResponse extends BaseResponse {
		
		String ImageName;
		byte[] Image;

		public ScreenImageResponse() {
		}

		public String getImageName() {
			return ImageName;
		}
		
		public byte[] getImage() {
			return Image;
		}

	}
}
