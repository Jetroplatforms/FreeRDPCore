package com.freerdp.freerdpcore.sharedobjects;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.freerdp.freerdpcore.sharedobjects.controller_messages.CockpitSiteInfoMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.ErrorMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.GetTsMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.KillProcessMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.LoginMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.LoginScreenImageMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.LogoutMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.MyApplicationsMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.ResetPasswordMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.SessionEndMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.SessionReadyMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.ShowKeyBoardMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.ShowTaskListMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.ShowWindowMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.StartApplicationMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.StartRdpMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.TicketValidationMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.WindowChangeMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.WindowCloseMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.WindowCreatedMsg;
import com.freerdp.freerdpcore.sharedobjects.controller_messages.WindowDestroyedMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.BaseMsg;
import com.freerdp.freerdpcore.sharedobjects.protocol.MessagesValues;
import com.freerdp.freerdpcore.sharedobjects.utils.Logger;
import com.freerdp.freerdpcore.sharedobjects.utils.Logger.LogLevel;

public class SocketManager {
	
	private static final String TAG = SocketManager.class.getSimpleName();

	private static SocketManager sInstance;
	private SocketChannel socket;
	private static ISocketListener mListener;
	private OutputStream os;
	private InputStream is;
	private Handler mHandler;
	String errorMessage;

	private Thread ListenerThread = null;
	private boolean abortListenerThread = false;

	private final int MAGIC_NUMBER = 0x34591021;
	
	private long timestampStart = 0;
	private long timestampEnd = 0;

	private final int MESSAGE_STATE_MAGIC_NUMBER = 0;
	private final int MESSAGE_STATE_MESSAGE_LENGTH = 1;
	private final int MESSAGE_STATE_MESSAGE_BODY = 2;

	private SocketManager(ISocketListener listener) {
		mListener = listener;
	}

	public static SocketManager getInstance(ISocketListener listener) {

		if (sInstance == null)
			sInstance = new SocketManager(listener);

		mListener = null;
		mListener = listener;

		return sInstance;
	}

	public void openSocket(final String ip, final int port, Handler h) {

		if (isConnected()) {
			try {
				socket.socket().close();
				socket = null;
			} catch (IOException e) {
			}
		}

		mHandler = h;
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Log.i(TAG, "SocketManager.openSocket(...).new Runnable() {...}#run(...) ip: " + ip);
					Log.i(TAG, "SocketManager.openSocket(...).new Runnable() {...}#run(...) port: " + + port);
					
					socket = SocketChannel.open();
					socket.connect(new InetSocketAddress(ip, port));

					startListenerThread();

					mHandler.post(new Runnable() {
						@Override
						public void run() {
							mListener.OnSocketCreated();
						}
					});

				} catch (IOException ioe) {
					Log.e(TAG, "ERROR: ", ioe);
					mListener.OnIOError("Retry");
				} catch (Exception e) {
					Log.e(TAG, "ERROR: ", e);
					mListener.OnIOError(e.getMessage());
				}
			}
		}).start();
	}

	/*
	 * Create the async listener thread
	 */
	private void startListenerThread() {

		if (!isConnected() || ListenerThread != null)
			return;

		abortListenerThread = false;

		ListenerThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				int inputBufferSize = 1024;
				int sleepDurationMilliseconds = 50;

				// set the socket timeout to 50ms. this will allow blocking
				// for small amount of time and keep the look running
				try {
					socket.socket().setSoTimeout(sleepDurationMilliseconds);
				} catch (SocketException e1) {
					e1.printStackTrace();
				}

				// input buffer: used for getting bytes from the socket only
				ByteBuffer inputBuffer = ByteBuffer
						.allocateDirect(inputBufferSize);

				// message buffer - accumulates bytes received from the socket
				ByteBuffer messageBuffer = ByteBuffer
						.allocateDirect(BaseMsg.MAX_MESSAGE_SIZE);
				int messageBufferLen = 0;
				int messageBufferPosition = 0;

				// the message: actual message contents that we assemble during
				// the process
				// then later on pass up to the application
				ByteBuffer theMessage = ByteBuffer
						.allocateDirect(BaseMsg.MAX_MESSAGE_SIZE);
				int theMessagePosition = 0;

				int expectedMessageLength = 0;

				int numBytesRead = 0;

				// state machine
				int state = MESSAGE_STATE_MAGIC_NUMBER;

				while (isConnected() && !abortListenerThread) {
					try {
						try {
							// make sure we don't read any more bytes from the
							// socket
							// unless we have room to store them in
							// messageBuffer
							// check if messageBufferLen + inputBufferSize <=
							// BaseMsg.MAX_MESSAGE_SIZE
							// and if so sleep sleepDurationMilliseconds
							if (messageBufferLen + inputBufferSize <= BaseMsg.MAX_MESSAGE_SIZE) {
								// clear the input buffer
								inputBuffer.clear();
								inputBuffer = clearBuffer(inputBuffer);

								// read from the socket (we have a timeout set
								// for the
								// socket to allow small blocking sleep)
								numBytesRead = socket.read(inputBuffer);
							} else {
								// no room to hold more bytes - we don't read
								// from the socket simply sleep a while
								Thread.sleep(sleepDurationMilliseconds);
							}

						} catch (Exception e) {
						}

						// if we received any new bytes, or there are still
						// bytes to handle
						if (numBytesRead > 0 || messageBufferLen > 0) {
							// append any new rx bytes to our working buffer
							if (numBytesRead > 0) {
								//inputBuffer.flip();
								messageBuffer.position(messageBufferLen);
								messageBuffer.put(inputBuffer.array(), 0,
										numBytesRead);
								messageBufferLen += numBytesRead;
							}

							switch (state) {
							case MESSAGE_STATE_MAGIC_NUMBER:
								
								Log.w(TAG, "----------------- GOT MAGIC ----------------- ");
								timestampStart = System.currentTimeMillis();

								// run through the buffer looking for the magic
								// number
								while ((messageBufferLen - messageBufferPosition) >= 4) {
									if (messageBuffer
											.getInt(messageBufferPosition) == MAGIC_NUMBER) {
										// throw away (shift left) all bytes
										// before and including the magic number
										messageBuffer = shiftLeft(
												messageBuffer,
												messageBufferPosition + 4);
										messageBuffer.position(0);
										messageBufferLen -= (messageBufferPosition + 4);
										messageBufferPosition = 0;

										// set the magic number in theMessage
										theMessage.putInt(theMessagePosition,
												MAGIC_NUMBER);
										theMessagePosition += 4;

										// move on to the next state
										state = MESSAGE_STATE_MESSAGE_LENGTH;

										break;
									} else {
										// step one byte forward
										messageBufferPosition++;
									}
								}

								break;

							case MESSAGE_STATE_MESSAGE_LENGTH:

								// if we have enough bytes (4) - grab the
								// message length
								if ((messageBufferLen - messageBufferPosition) >= 4) {
									expectedMessageLength = messageBuffer
											.getInt(messageBufferPosition);
									
									// throw away all bytes before and including
									// the message length
									messageBuffer = shiftLeft(messageBuffer,
											messageBufferPosition + 4);
									messageBuffer.position(0);
									messageBufferLen -= (messageBufferPosition + 4);
									messageBufferPosition = 0;

									// set the message length in theMessage
									theMessage.putInt(theMessagePosition,
											expectedMessageLength);
									theMessagePosition += 4;

									// move on to hext state
									state = MESSAGE_STATE_MESSAGE_BODY;
								}

								break;

							case MESSAGE_STATE_MESSAGE_BODY:

								// wait until we get all required message data
								// bytes of this message
								if ((messageBufferLen - messageBufferPosition) >= (expectedMessageLength - 8)) {
									// copy message bytes into theMessage
									theMessage.position(theMessagePosition);
									theMessage.put(messageBuffer.array(),
											messageBufferPosition,
											expectedMessageLength - 8);
									theMessagePosition += (expectedMessageLength - 8);

									// throw away all bytes before and including
									// the message body
									messageBuffer = shiftLeft(messageBuffer,
											expectedMessageLength - 8);
									messageBuffer.position(0);
									messageBufferLen -= (messageBufferPosition
											+ expectedMessageLength - 8);
									messageBufferPosition = 0;

									try {
										// unpack the message
										BaseMsg msg = createMessage(theMessage);
										final BaseMsg unpackedMessage = msg
												.unpack(theMessage.array());

										// send the message to the listener
										// guard by try-catch in case the
										// listener misbehaves
										try {
											mHandler.post(new Runnable() {

												@Override
												public void run() {
													timestampEnd = System.currentTimeMillis();
													Log.w(TAG, "----------- Message Parsed Time -----------" + (timestampEnd - timestampStart));
													mListener.OnMessageReceived(unpackedMessage);
												}
											});
										} catch (Exception e) {
											Logger.log(LogLevel.ERROR,
													"Listener threw an exception: "
															+ e.getMessage());
										}
									} catch (Exception e) {
										// TODO: think about this - do we show
										// an error message to the user?
									}

									// switch back to state zero: look for next
									// magic number
									state = MESSAGE_STATE_MAGIC_NUMBER;

									// clear buffers and variables for next
									// message
									theMessage = clearBuffer(theMessage);
									theMessage.clear();
									theMessagePosition = 0;
									expectedMessageLength = 0;
								}

								break;

							default:
								// TOOD: handle unknown state
								break;
							}
						}
					} catch (Exception e) {
						mListener.OnIOError(e.getMessage());
						e.printStackTrace();
					}
				}

				ListenerThread = null;
			}
		});

		ListenerThread.start();
	}

	private BaseMsg createMessage(ByteBuffer buffer) {
		BaseMsg msg = null;

		short classID = buffer.getShort(17);
		Log.i(TAG, "SocketManager#createMessage(...) Received message: " + classID);

		switch (classID) {
		case MessagesValues.ClassID.CockpitSiteInfoMsg:
			msg = new CockpitSiteInfoMsg();
			break;

		case MessagesValues.ClassID.LoginMsg:
			msg = new LoginMsg();
			break;

		case MessagesValues.ClassID.LoginScreenImageMsg:
			msg = new LoginScreenImageMsg();
			break;

		case MessagesValues.ClassID.ResetPasswordMsg:
			msg = new ResetPasswordMsg();
			break;

		case MessagesValues.ClassID.MyApplicationsMsg:
			msg = new MyApplicationsMsg();
			break;

		case MessagesValues.ClassID.GetTsMsg:
			msg = new GetTsMsg();
			break;

		case MessagesValues.ClassID.LogoutMsg:
			msg = new LogoutMsg();
			break;

		case MessagesValues.ClassID.TicketValidationMsg:
			msg = new TicketValidationMsg();
			break;

		case MessagesValues.ClassID.ShowWindowMsg:
			msg = new ShowWindowMsg();
			break;

		case MessagesValues.ClassID.StartApplicationMsg:
			msg = new StartApplicationMsg();
			break;

		case MessagesValues.ClassID.ShowTaskListMsg:
			msg = new ShowTaskListMsg();
			break;

		case MessagesValues.ClassID.WindowDestroyedMsg:
			msg = new WindowDestroyedMsg();
			break;

		case MessagesValues.ClassID.ShowKeyBoardMsg:
			msg = new ShowKeyBoardMsg();
			break;

		case MessagesValues.ClassID.WindowCreatedMsg:
			msg = new WindowCreatedMsg();
			break;

		case MessagesValues.ClassID.KillProcessMsg:
			msg = new KillProcessMsg();
			break;

		case MessagesValues.ClassID.SessionReadyMsg:
			msg = new SessionReadyMsg();
			break;

		case MessagesValues.ClassID.StartRdpMsg:
			msg = new StartRdpMsg();
			break;

		case MessagesValues.ClassID.WindowCloseMsg:
			msg = new WindowCloseMsg();
			break;

		case MessagesValues.ClassID.SessionEndMsg:
			msg = new SessionEndMsg();
			break;
			
		case MessagesValues.ClassID.WindowChangeMsg:
			msg = new WindowChangeMsg();
			break;			
			
		case MessagesValues.ClassID.Error:
			msg = new ErrorMsg();
			break;
			
		default:
			break;
		}

		return msg;
	}

	private ByteBuffer clearBuffer(ByteBuffer b) {
		b.clear();
		/*
		for (int i = 0; i < b.limit(); i++)
			b.put(i, (byte) 0);
		*/
		return b;
	}

	private ByteBuffer shiftLeft(ByteBuffer b, int n) {
		for (int i = n; i < b.limit() + n; i++) {
			if (i < b.limit()) {
				b.put(i - n, b.get(i));
			} else {
				b.put(i - n, (byte) 0);
			}
		}

		return b;
	}

	/*
	 * Stops (aborts) the async listener thread
	 */
	private void abortListenerThread() {
		abortListenerThread = true;
	}

	public void sendMessage(final BaseMsg msg) {

		if (!isConnected()) {
			mListener.OnIOError(msg.toString());
			return;
		}
		//

		new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				try {
					os = socket.socket().getOutputStream();
					os.write(msg.pack());
					os.flush();
				} catch (IOException e) {
					Log.e(TAG, "ERROR: ", e);
					mListener.OnIOError("Disconnect");
				}
				return null;
			}

			/*
			 * @Override protected void onPostExecute(Void result) {
			 * readFromStream(msg); };
			 */
		}.execute();
	}

	private void readFromStream(final BaseMsg msg) {

		if (!isConnected()) {
			mListener.OnIOError("Socket is closed");
			return;
		}

		new AsyncTask<Void, Void, BaseMsg>() {
			@Override
			protected BaseMsg doInBackground(Void... params) {
				byte[] readIn = new byte[BaseMsg.MAX_MESSAGE_SIZE];

				try {
					// Reading the content of the socket input stream.
					is = socket.socket().getInputStream();

					// The Problem -> when all happening fast, in big data
					// messages (binary bitmaps), the stream was corrupted,
					// it means that not all of the bytes was read properly
					// from the stream.
					//
					// The Solution -> put the thread to sleep for less then
					// a second so that all the stream arrives
					// properly

					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// read bytes from the stream
					is.read(readIn);
					try {
						Thread.sleep(800);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					mListener.OnIOError(e.getMessage());
					e.printStackTrace();
				}

				BaseMsg result = msg.unpack(readIn);
				readIn = null;
				return result;
			}

			protected void onPostExecute(BaseMsg result) {
				mListener.OnMessageReceived(result);
			};
		}.execute();
	}

	public void closeSocket() {
		try {
			socket.close();
			abortListenerThread();
			Logger.log(LogLevel.INFO, "Zacky: Socket Closed");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
		}
	}

	public boolean isConnected() {
		return (socket != null && socket.socket().isConnected());
	}
}
