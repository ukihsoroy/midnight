package org.ko.prototype.support.constants;

public class AppConstants {
	
	public static final class Session {
		public static final String UserId = "userId";
//		public static final String Username = "username";
		public static final String User = "user";
		public static final String RegisteredUser = "registeredUser";
		public static final PreviousQueryCriteria PreviousQueryCriteria = new PreviousQueryCriteria();
	}
	
	public static final class PreviousQueryCriteria {
		private PreviousQueryCriteria(){}
		
		public String get(String name){
			return name + ".criteria";
		}
	}
	
	public static final class AuditRequestParameter{
		public static final String PageCode = "_p";
		public static final String RandomSequence = "_r";
		public static final String RequestUrl = "_s";
		public static final String Channel = "_c";
	}
}
