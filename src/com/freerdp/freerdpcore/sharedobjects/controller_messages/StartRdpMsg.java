package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartRdpMsg extends BaseMsg {
	
	@Expose @SerializedName("Address")
	private String address;
	@Expose @SerializedName("Port")
	private int port;
	
	private StartRdpMsgResponse resposne;
	
	public StartRdpMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.WindowDestroyedMsg;
	}
	
	public StartRdpMsg(String address, int port) {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.WindowDestroyedMsg;
		this.address = address;
		this.port = port;
	}
	
	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, StartRdpMsgResponse.class);
		} catch (Exception e) {
			resposne = new StartRdpMsgResponse();
		}
	}
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class StartRdpMsgResponse extends BaseResponse {

		public StartRdpMsgResponse() {
		}
		
	}
}
