package com.freerdp.freerdpcore.sharedobjects.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

import android.util.Log;

public class Logger {
	// TODO:disable logs if needed
	private static final boolean DISPLAY_LOGS = true;
	/**
	 * the format of the message to log , when the first string is the method
	 * name , and the second is the input message itself
	 */
	private static final String LOG_MESSAGE_FORMAT_WHEN_APPLICATION_TAG_DOESNT_EXIST = "%s:%s";
	/**
	 * the format of the message to log when the logger has an application tag,
	 * when the first string is the class name , the second is the method name ,
	 * and the third is the message itself
	 */
	private static final String LOG_MESSAGE_FORMAT_WHEN_APPLICATION_TAG_EXISTS = "%s.%s:%s";
	private static String _applicationTag = null;

	public enum LogLevel {
		VERBOSE, DEBUG, INFO, WARNING, ERROR
	}

	/**
	 * sets an application tag to be used , so that all log messages will be
	 * inside this tag.<br />
	 * if the application tag is null (or if never called to this function) ,
	 * each log message will have a tag that matches the class of the function
	 * that called the log function, <br />
	 * otherwise, all log messages will be inside the application tag
	 */
	public static void setApplicationTag(final String applicationTag) {
		_applicationTag = applicationTag;
	}

	public static String getTag() {
		if (_applicationTag == null) {
			final StackTraceElement stackTraceElement = Thread.currentThread()
					.getStackTrace()[3];
			final String fullClassName = stackTraceElement.getClassName();
			final String simpleClassName = fullClassName
					.substring(fullClassName.lastIndexOf('.') + 1);

			return simpleClassName;
		} else
			return _applicationTag;
	}

	/** writes a message to the log , using the specified log level */
	public static void log(final LogLevel logLevel, final String message) {
		if (!DISPLAY_LOGS && logLevel != LogLevel.ERROR)
			return;
		final StackTraceElement stackTraceElement = Thread.currentThread()
				.getStackTrace()[3];
		final String fullClassName = stackTraceElement.getClassName();
		final String simpleClassName = fullClassName.substring(fullClassName
				.lastIndexOf('.') + 1);
		final String fullMessage, logTag;
		final String currentMethodName = stackTraceElement.getMethodName();

		if (_applicationTag == null)
			fullMessage = String.format(
					LOG_MESSAGE_FORMAT_WHEN_APPLICATION_TAG_DOESNT_EXIST,
					currentMethodName, message);
		else
			fullMessage = String.format(
					LOG_MESSAGE_FORMAT_WHEN_APPLICATION_TAG_EXISTS,
					simpleClassName, currentMethodName, message);

		switch (logLevel) {
		case DEBUG:
			Log.d(getTag(), fullMessage);
			break;
		case ERROR:
			Log.e(getTag(), fullMessage);
			break;
		case INFO:
			Log.i(getTag(), fullMessage);
			break;
		case VERBOSE:
			Log.v(getTag(), fullMessage);
			break;
		case WARNING:
			Log.w(getTag(), fullMessage);
			break;
		}
	}

	public static void e(final Exception e, final String message) {
		if (e != null) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();

			Log.e(getTag(), message + ": " + e.getMessage() + ": " + stacktrace);
		} else
			Log.e(getTag(), message);
	}

	public static void e(final String TAG, final Exception e,
			final String message) {
		if (e != null) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();

			Log.e(TAG, message + ": " + e.getMessage() + ": " + stacktrace);
		} else
			Log.e(TAG, message);
	}

	public static void w(final Exception e, final String message) {
		if (!DISPLAY_LOGS)
			return;

		if (e != null) {
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			String stacktrace = sw.toString();

			Log.w(getTag(), message + ": " + e.getMessage() + ": " + stacktrace);
		} else
			Log.w(getTag(), message);
	}

	public static void d(final String TAG, final String message) {
		if (!DISPLAY_LOGS)
			return;

		Log.d(TAG, message);
	}

	public static void d(final String TAG, final String message, final int value) {
		if (!DISPLAY_LOGS)
			return;

		Logger.d(TAG, message + ": " + value);
	}

	/**
	 * Will write a message to the log in a very noticeable way
	 * 
	 * @param TAG
	 * @param message
	 */
	public static void shout(final String TAG, final String message) {
		if (!DISPLAY_LOGS)
			return;

		for (int i = 0; i < 10; i++)
			Logger.d(TAG, message);
	}

	/**
	 * Will write a message to the log in a very noticeable way
	 * 
	 * @param TAG
	 * @param message
	 */
	public static void shout(final String message) {
		if (!DISPLAY_LOGS)
			return;

		for (int i = 0; i < 10; i++)
			Logger.d(getTag(), message);
	}
}
