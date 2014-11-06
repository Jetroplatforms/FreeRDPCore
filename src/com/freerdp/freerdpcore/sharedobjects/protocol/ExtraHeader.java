package com.freerdp.freerdpcore.sharedobjects.protocol;

public class ExtraHeader {

	public byte MajorProtolVer;
	public byte MinorProtolVer;
	public String SourceIDLength;
	public String DestinationIDLength;
	public byte Flags;
	public byte Reserved;
	public byte MsgCategory;
	public short MsgClassID;
	public byte EncryptionMethod;
	public byte SeralizationMethod;

	public ExtraHeader() {
		// TODO Auto-generated constructor stub
	}
}
