package com.freerdp.freerdpcore.sharedobjects.utils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class ReadWriteOperators {

	public static int offset = 0;

	private ReadWriteOperators() {

	}

	public static void writeInt(int data, byte[] buf) {
		ByteBuffer.wrap(buf).putInt(offset, data);
		offset += 4;
	}

	public static void writeByte(byte data, byte[] buf) {
		ByteBuffer.wrap(buf).put(offset, data);
		offset += 1;
	}

	public static void writeShort(short data, byte[] buf) {
		ByteBuffer.wrap(buf).putShort(offset, data);
		offset += 2;
	}

	public static void writeBinary(byte[] data, byte[] buf) {
		System.arraycopy(data, 0, buf, offset, data.length);
		offset += data.length;
	}

	public static int readInt(byte[] buf) {
		int result = ByteBuffer.wrap(buf).getInt(offset);
		offset += 4;
		return result;
	}

	public static short readShort(byte[] buf) {
		short result = ByteBuffer.wrap(buf).getShort(offset);
		offset += 2;
		return result;
	}

	public static byte readByte(byte[] buf) {
		byte result = ByteBuffer.wrap(buf).get(offset);
		offset += 1;
		return result;
	}

	public static String readString(byte[] buf) {
		byte b = readByte(buf);
		String s = null;
		try {
			s = new String(buf, offset, (int) b, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		offset += (int) b;
		return s;
	}

	public static byte[] readBinary(byte[] buf) {
		int l = readInt(buf);
		byte[] result = new byte[l];
		System.arraycopy(buf, offset, result, 0, l);
		offset += (int) l;
		return result;
	}
}
