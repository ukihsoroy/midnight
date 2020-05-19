package org.ko.analysis.store.master.constants;

import org.ko.analysis.store.bean.BasicColumnConstants;

public class DictConstants {

    public static class Columns extends BasicColumnConstants {

        /**
         * 主键
         */
        public static String ID = "id";

        /**
         * 字典编码
         */
        public static String CODE = "code";

        /**
         * 字典名称
         */
        public static String VALUE = "value";

        /**
         * 字典类型
         */
        public static String TYPE = "type";

        /**
         * 描述
         */
        public static String DESCRIPTION = "description";

        /**
         * 数据状态：0-有效，1-删除
         */
        public static String DISABLE = "disable";
    }

}