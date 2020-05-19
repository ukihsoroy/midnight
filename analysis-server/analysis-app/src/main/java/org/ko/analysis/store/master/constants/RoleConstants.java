package org.ko.analysis.store.master.constants;


import org.ko.analysis.store.bean.BasicColumnConstants;

public class RoleConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 角色编码，由系统统一生成，不可修改
         */
        public static String CODE = "code";

        /**
         * 角色名称
         */
        public static String NAME = "name";
    }

}