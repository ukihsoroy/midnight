package org.ko.analysis.store.master.constants;

import org.ko.analysis.store.bean.BasicColumnConstants;

public class UserRoleConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 用户编码（UUID），由系统统一生成，不可修改
         */
        public static String USER_ID = "user_id";

        /**
         * 角色编码，由系统统一生成，不可修改
         */
        public static String ROLE_CODE = "role_code";
    }

}