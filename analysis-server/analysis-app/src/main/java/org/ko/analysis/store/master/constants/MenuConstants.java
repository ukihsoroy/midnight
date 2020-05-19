package org.ko.analysis.store.master.constants;


import org.ko.analysis.store.bean.BasicColumnConstants;

public class MenuConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 菜单名称
         */
        public static String NAME = "name";

        /**
         * 菜单路由规则索引
         */
        public static String PATH = "path";

        /**
         * 命名视图组件
         */
        public static String COMPONENT = "component";

        /**
         * 路由重定向
         */
        public static String REDIRECT = "redirect";

        /**
         * 菜单元数据。 name: 路由名称，icon: 路由图标
         */
        public static String META_JSON = "meta_json";

        /**
         * 上级菜单编码，由系统统一生成，不可修改
         */
        public static String PARENT_ID = "parent_id";
    }

}