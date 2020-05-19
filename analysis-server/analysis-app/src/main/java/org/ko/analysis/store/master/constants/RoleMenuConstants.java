package org.ko.analysis.store.master.constants;


import org.ko.analysis.store.bean.BasicColumnConstants;

public class RoleMenuConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 角色编码，由系统统一生成，不可修改
         */
        public static String ROLE_CODE = "role_code";

        /**
         * 菜单编码，由系统统一生成，不可修改
         */
        public static String MENU_ID = "menu_id";

        /**
         * 创建数据权限：0-否，1-是
         */
        public static String CREATE = "create";

        /**
         * 读取详情权限：0-否，1-是
         */
        public static String READ = "read";

        /**
         * 更新数据权限：0-否，1-是
         */
        public static String UPDATE = "update";

        /**
         * 删除数据权限：0-否，1-是
         */
        public static String DELETE = "delete";
    }

}