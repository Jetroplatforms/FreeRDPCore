package com.freerdp.freerdpcore.sharedobjects;

public class Task {
	int HWND;
	int PID;
	String Title;
	byte[] Icon;
	String AppID;

	public int getHWND() {
		return HWND;
	}

	public int getPID() {
		return PID;
	}

	public String getTitle() {
		return Title;
	}
	
	public byte[] getIcon() {
		return Icon;
	}
	
	public String getAppID() {
		return AppID;
	}
}
