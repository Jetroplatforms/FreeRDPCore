package com.freerdp.freerdpcore.sharedobjects.protocol;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import android.util.Log;

import com.freerdp.freerdpcore.sharedobjects.utils.Logger;
import com.freerdp.freerdpcore.sharedobjects.utils.Logger.LogLevel;
import com.freerdp.freerdpcore.sharedobjects.utils.ReadWriteOperators;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public abstract class BaseMsg {
	
	private static final String TAG = BaseMsg.class.getSimpleName();
	
	// Constants
	private final int MagicNumber = 0x34591021;
	private int HEADER_LENGTH_ = 12;
	private short EXTRAHEADER_LENGTH_MIN = 11;
	private byte Flags = 0;
	private byte Reserved = 0;
	private byte MAJOR_VERSION = 4;
	private byte MINOR_VERSION = 5;
	private byte XML = 0;
	private byte Binary = 1;
	private byte JSON = 2;
	private byte EncryptionMethodNone = 0;
	private byte EncryptionMethodRSA1024 = 1;
	public static int MAX_MESSAGE_SIZE = 65536;

	// Members & Properties
	public byte msgCategory;
	public short msgCalssID;
	public byte[] JsonData;
	public byte[] Buffer;
	public Header header;
	public ExtraHeader extraHeader;
	@Expose @SerializedName("MsgID")
	public String MsgId = UUID.randomUUID().toString();
	private String json = null;

	/**
	 * parse the JSON string to object
	 * 
	 * @param response
	 *            - the JSON string from server
	 */
	public abstract void deserializeJson(String s);

	/**
	 * returns the JSON object parsed from server response
	 * 
	 * @return - the parsed JSON object
	 */
	public abstract BaseResponse getJsonResponse();

	/**
	 * prepare all data in byte buffer for sending to socket stream
	 * 
	 * @return - the byte buffer accumulated with all data as bytes
	 */
	public byte[] pack() {

		try {
			// parse JSON to string
			Gson g = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
			json = g.toJson(this, getClass());
			String msg = json;
			// get string as bytes
			if (msg == null) {
				msg = "";
			}
			JsonData = msg.getBytes("UTF-8");

			// whole message length
			Buffer = new byte[HEADER_LENGTH_ + EXTRAHEADER_LENGTH_MIN + 4
					+ JsonData.length];

		} catch (UnsupportedEncodingException e) {
			Log.i("encode", "exception in codeing stream");
			e.printStackTrace();
		} catch (Exception e) {
			Log.i("encode", "exception in encoding!");
			e.printStackTrace();
		}

		ReadWriteOperators.offset = 0;

		/**
		 * Header
		 */
		ReadWriteOperators.writeInt(MagicNumber, Buffer);
		// Message length
		ReadWriteOperators.writeInt(Buffer.length, Buffer);
		// Extra header length
		ReadWriteOperators.writeShort(EXTRAHEADER_LENGTH_MIN, Buffer);
		// Flags
		ReadWriteOperators.writeByte(Flags, Buffer);
		// Reserved
		ReadWriteOperators.writeByte(Reserved, Buffer);

		/**
		 * Extra header
		 */
		// Major version
		ReadWriteOperators.writeByte(MAJOR_VERSION, Buffer);
		// Minor version
		ReadWriteOperators.writeByte(MINOR_VERSION, Buffer);
		// Source id length - currently = 0
		ReadWriteOperators.writeByte((byte) 0, Buffer);
		// Destination id length - currently = 0
		ReadWriteOperators.writeByte((byte) 0, Buffer);
		// Message category
		ReadWriteOperators.writeByte(msgCategory, Buffer);
		// Message class id
		ReadWriteOperators.writeShort(msgCalssID, Buffer);
		// Encryption Method - currently none = 0
		ReadWriteOperators.writeByte(EncryptionMethodNone, Buffer);
		// Serialization Method - currently JSON = 2
		ReadWriteOperators.writeByte(JSON, Buffer);
		// Flags
		ReadWriteOperators.writeByte(Flags, Buffer);
		// Reserved
		ReadWriteOperators.writeByte(Reserved, Buffer);
		// Json data length
		ReadWriteOperators.writeInt(JsonData.length, Buffer);
		// Write json
		ReadWriteOperators.writeBinary(JsonData, Buffer);
		
		int size = Buffer.length;
		Log.w(TAG, "Message: " + MessagesValues.ClassID.getName(msgCalssID));
		Log.i(TAG, "BaseMsg#pack(...) json message length: " + size + "\n json message: " + json);

		return Buffer;
	}

	/**
	 * unpack all message
	 * 
	 * @param buf
	 *            - the byte buffer from socket
	 */
	public BaseMsg unpack(byte[] buf) {

		if (buf.length < HEADER_LENGTH_)
			return null;

		// if headers unpacking fail, stop all
		if (!unpackHeaders(buf)) {
			Logger.log(LogLevel.ERROR, "Headers unpacking failed");
			return null;
		}

		if (buf.length < header.MessageLength) {
			return null;
		}

		int size = buf.length;
		json = new String(ReadWriteOperators.readBinary(buf));
		Log.w(TAG, "Message: " + MessagesValues.ClassID.getName(msgCalssID));
		Log.i(TAG, "BaseMsg#unpack(...) json message length: " + size + "\n json message: " + json);
		
		String s = json;
		deserializeJson(s);

		return this;
	}

	/**
	 * unpack response headers
	 * 
	 * @param buf
	 *            - the byte buffer from socket
	 * @return - return true if all headers are valid , false otherwise
	 */
	private boolean unpackHeaders(byte[] buf) {

		header = new Header();
		extraHeader = new ExtraHeader();
		ReadWriteOperators.offset = 0;

		if (MagicNumber != ReadWriteOperators.readInt(buf)) {
			return false;// TODO: exception
		}

		/**
		 * Header
		 */
		header.MessageLength = ReadWriteOperators.readInt(buf);
		header.ExtraHeaderLength = ReadWriteOperators.readShort(buf);
		header.Flags = ReadWriteOperators.readByte(buf);
		header.Reserved = ReadWriteOperators.readByte(buf);

		/**
		 * Extra header
		 */
		extraHeader.MajorProtolVer = ReadWriteOperators.readByte(buf);
		extraHeader.MinorProtolVer = ReadWriteOperators.readByte(buf);
		extraHeader.SourceIDLength = ReadWriteOperators.readString(buf);
		extraHeader.DestinationIDLength = ReadWriteOperators.readString(buf);
		extraHeader.MsgCategory = ReadWriteOperators.readByte(buf);
		extraHeader.MsgClassID = ReadWriteOperators.readShort(buf);
		extraHeader.EncryptionMethod = ReadWriteOperators.readByte(buf);
		extraHeader.SeralizationMethod = ReadWriteOperators.readByte(buf);
		extraHeader.Flags = ReadWriteOperators.readByte(buf);
		extraHeader.Reserved = ReadWriteOperators.readByte(buf);

		return true;
	}

	@Override
	public final String toString() {
		return json;
	}
	
}
