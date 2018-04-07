package org.ko.prototype.admin.constants;

public final class WebSocketConstants {

	public static final class Config {
		public static final String EndPoint = "/ws";
		public static final String BrokerPrefix = "/topic";
		public static final String AppDestinationPrefix = "/app";
	}
	
	public static final class EndPoint {
		public static final String Task = Config.BrokerPrefix + "/task";
	}
}
