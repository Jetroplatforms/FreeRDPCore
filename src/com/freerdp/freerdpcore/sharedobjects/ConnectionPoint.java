package com.freerdp.freerdpcore.sharedobjects;

import android.os.Parcel;
import android.os.Parcelable;

public class ConnectionPoint implements Parcelable {

	boolean WAN;
	String IP;
	int Port;
	String Name;
	boolean SSL;
	String UserName;
	String Domain;
	String Password;
	String ConnectionMode;

	public ConnectionPoint() {
	}

	public ConnectionPoint(Parcel in) {
		this.WAN = in.readByte() == 0;
		this.IP = in.readString();
		this.Port = in.readInt();
		this.Name = in.readString();
		this.WAN = in.readByte() == 0;
		this.UserName = in.readString();
		this.Domain = in.readString();
		this.ConnectionMode = in.readString();
	}

	public void setName(String name) {
		Name = name;
	}

	public void setConnectionMode(String mode) {
		ConnectionMode = mode;
	}

	public String getConnectionMode() {
		return ConnectionMode;
	}

	public String getName() {
		return Name;
	}

	public void setWAN(boolean wAN) {
		WAN = wAN;
	}

	public void setDomain(String domain) {
		this.Domain = domain;
	}

	public void setUserName(String userName) {
		this.UserName = userName;
	}

	public void setPassword(String password) {
		this.Password = password;
	}

	public String getDomain() {
		return Domain;
	}

	public String getUserName() {
		return UserName;
	}

	public String getPassword() {
		return Password;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public void setPort(int port) {
		Port = port;
	}

	public void setSSL(boolean sSL) {
		SSL = sSL;
	}

	public boolean isWAN() {
		return WAN;
	}

	public String getIP() {
		return IP;
	}

	public int getPort() {
		return Port;
	}

	public boolean isSSL() {
		return SSL;
	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public ConnectionPoint createFromParcel(Parcel in) {
			return new ConnectionPoint(in);
		}

		public ConnectionPoint[] newArray(int size) {
			return new ConnectionPoint[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeByte((byte) (this.WAN == true ? 0 : 1));
		dest.writeString(this.IP);
		dest.writeInt(this.Port);
		dest.writeString(this.Name);
		dest.writeByte((byte) (this.SSL == true ? 0 : 1));
		dest.writeString(this.UserName);
		dest.writeString(this.Domain);
		dest.writeString(this.ConnectionMode);
	}

	@Override
	public String toString() {
		return this.IP;
	}
	
}
