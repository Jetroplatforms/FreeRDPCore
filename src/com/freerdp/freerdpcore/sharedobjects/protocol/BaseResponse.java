package com.freerdp.freerdpcore.sharedobjects.protocol;


public class BaseResponse {

	private String MsgID;
	private String Ticket;
	private int Err;
	private String Description;

	public BaseResponse() {
	}
	
	public String getMsgID() {
		return MsgID;
	}

	public String getTicket() {
		return Ticket;
	}
	
	public int getErr() {
		return Err;
	}

	public String getDescription() {
		return Description;
	}
	
}
