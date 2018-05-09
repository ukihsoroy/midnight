package org.ko.prototype.core.constants;

public final class RestfulConstraint {

	public static final class MaxLength {
		public static final int Username = 20;
		public static final int Password = 20;
		public static final int Token = 50;
		public static final int Flag = 2;
		public static final int Url = 200;
		public static final int Description = 500;
		public static final int NormalText = 50;
		public static final int NormalNumber = 3;
		public static final int ImageToken = 4;
	}
	
	public static final class Mobile {
		public static final String Pattern = "^1[0-9]{10}";
		public static final String WithEmptyPattern = "^$|" + Pattern;
		public static final String Message = "手机号必须是11位，以1开头";
	}
	
	public static class Email {
		public static final String Message = "无效的邮箱格式";
	}
	
	public static class Date {
		public static final String Pattern = "\\d{4}\\-\\d{2}\\-\\d{2}";
		public static final String WithEmptyPattern = "^$|" + Pattern;
		public static final String Message = "日期格式不正确";
	}
	
	public static class Id {
		public static final String Pattern = "[1-9]{1}\\d*";
		public static final String WithEmptyPattern = "\\d*";
	}

	public static class Storage {
		public static final String Pattern = "user|archive|^$";
	}
	
//	public static class PositiveInteger {
//		public static final String Pattern = "[1-9]+|[1-9]+[0-9]+^$";
//		public static final String Message = "必须是正整数";
//	}
}
