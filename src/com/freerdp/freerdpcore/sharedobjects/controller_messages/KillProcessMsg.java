package com.freerdp.freerdpcore.sharedobjects.controller_messages;

import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseResponse;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues.ClassID;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KillProcessMsg extends BaseMsg {
	
	@Expose @SerializedName("PID")
	private int pId;
	
	private KillProcessMsgResponse resposne;
	
	public KillProcessMsg() {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.KillProcessMsg;
	}
	
	public KillProcessMsg(int pId) {
		msgCategory = MessagesValues.MOBILE_TS;
		msgCalssID = ClassID.KillProcessMsg;
		this.pId = pId;
	}

	@Override
	public void deserializeJson(String s) {
		try {
			Gson g = new Gson();
			resposne = g.fromJson(s, KillProcessMsgResponse.class);
		} catch (Exception e) {
			resposne = new KillProcessMsgResponse();
		}
	}
	
	@Override
	public BaseResponse getJsonResponse() {
		return resposne;
	}
	
	public static class KillProcessMsgResponse extends BaseResponse {
		
		int PID;
		
		public KillProcessMsgResponse() {
		}

		public int getPID() {
			return PID;
		}
	}
}
