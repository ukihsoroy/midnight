package org.ko.analysis.store.master.constants;


import org.ko.analysis.store.bean.BasicColumnConstants;

public class SendCodeLogConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键ID
         */
        public static String ID = "id";

        /**
         * 接收方
         */
        public static String RECEIVE_ADDRESS = "receive_address";

        /**
         * 发送消息类型
         */
        public static String SEND_TYPE = "send_type";

        /**
         * 消息类型
         */
        public static String MESSAGE_TYPE = "message_type";

        /**
         * 消息代码
         */
        public static String MESSAGE_CODE = "message_code";

        /**
         * 失效时间，单位秒
         */
        public static String EXPIRE_IN = "expire_in";

        /**
         * 逻辑删除状态
         */
        public static String DISABLE = "disable";
    }

}