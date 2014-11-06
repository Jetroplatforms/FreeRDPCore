package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetTsMsg extends BaseMsg {

	@Expose @SerializedName("Ticket")
	String ticket;
	
	private GetTsMsgResponse response;
	
	public GetTsMsg() {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.GetTsMsg;
	}

	public GetTsMsg(String ticket) {
		msgCategory = MessagesValues.MOBILE_CONTROLLER;
		msgCalssID = ClassID.GetTsMsg;
		this.ticket = ticket;
	}

	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			response = g.fromJson(s, GetTsMsgResponse.class);
		} catch (Exception e) {
			response = new GetTsMsgResponse();
		}
	}

	@Override
	public BaseResponse getJsonResponse() {
		return response;
	}

	public class GetTsMsgResponse extends BaseResponse {

		String Address;
		int Port;
		String CommandLine;

		public GetTsMsgResponse() {
		}

		public String getAddress() {
			return Address;
		}

		public int getPort() {
			return Port;
		}

		public String getCommandLine() {
			return CommandLine;
		}
	}
}
