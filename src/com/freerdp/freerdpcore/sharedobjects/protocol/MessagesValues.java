package com.freerdp.freerdpcore.sharedobjects.protocol;

public class MessagesValues {

	// Message Category
	public static final byte GENERIC = 1;
	public static final byte MOBILE_CONTROLLER = 51;
	public static final byte MOBILE_TS = 52;

	public static class ClassID {

		public static final short None = 0;
		public static final short CockpitSiteInfoMsg = 5100;
		public static final short LoginScreenImageMsg = 5101;
		public static final short LoginMsg = 5102;
		public static final short ResetPasswordMsg = 5103;
		public static final short MyApplicationsMsg = 5104;
		public static final short GetTsMsg = 5105;
		public static final short LogoutMsg = 5106;
		public static final short TicketValidationMsg = 5107;
		
		public static final short ShowWindowMsg = 5201;
		public static final short StartApplicationMsg = 5202;
		public static final short ShowTaskListMsg = 5203;
		public static final short WindowDestroyedMsg = 5204;
		public static final short ShowKeyBoardMsg = 5205;
		public static final short WindowCreatedMsg = 5206;
		public static final short KillProcessMsg = 5207;
		public static final short SessionReadyMsg = 5208;
		public static final short StartRdpMsg = 5209;
		
		public static final short SessionEndMsg = 5211;
		public static final short WindowCloseMsg = 5212;
		public static final short WindowChangeMsg = 5213;
		
		public static final short Error = 9999;
		
		public static String getName(short id) {
			switch (id) {
			case 0:
				return "None";
			case 5100:
				return "CockpitSiteInfoMsg";
			case 5101:
				return "LoginScreenImageMsg";
			case 5102:
				return "LoginMsg";
			case 5103:
				return "ResetPasswordMsg";
			case 5104:
				return "MyApplicationsMsg";
			case 5105:
				return "GetTsMsg";
			case 5106:
				return "LogoutMsg";
			case 5107:
				return "TicketValidationMsg";
			case 5201:
				return "ShowWindowMsg";
			case 5202:
				return "StartApplicationMsg"; 
			case 5203:
				return "ShowTaskListMsg";
			case 5204:
				return "WindowDestroyedMsg";
			case 5205:
				return "ShowKeyBoardMsg";
			case 5206:
				return "WindowCreatedMsg";
			case 5207:
				return "KillProcessMsg";
			case 5208:
				return "SessionReadyMsg";
			case 5209:
				return "StartRdpMsg";
			case 5211:
				return "SessionEndMsg";
			case 5212:
				return "WindowCloseMsg";
			case 5213:
				return "WindowChangeMsg";				
			case 9999:
				return "Error";
			default:
				return null;
			}
		}
	}
	
	public static class ErrCode {
		public static final short LoginFailed = 112;
		public static final short ResetPasswordFailed = 155;
		public static final short NoneAvailableTS = 105;
		public static final short StartAppFailed = 121;
		public static final short InvalidTicket = 104;
		public static final short TimeoutError = 5;
		public static final short UnexpectedError = 999;
	}
	
	public static class LoginReturnCode {
		public static final short Error = 0;
		public static final short LoginSuccess = 1;
		public static final short ResetPassword = 2;
		public static final short PasswordBeforeExpiration = 3;
	}	

	public enum ClassID2 {
		
		CockpitSiteInfoMsg(5001);

		int classId = 0;

		private ClassID2(int id) {
			classId = id;
		}

		public short getIdAsByte() {
			return (short) classId;
		}
	}
}
