package ${rootPackage}.constants;

import org.ko.sigma.data.bean.BasicColumnConstants;

public class ${entityName}Constants {

    public static class Columns extends BasicColumnConstants {
    <#list columns as column>

        /**
         * ${column.comment}
         */
        public static String ${column.columnName?upper_case} = "${column.columnName}";
    </#list>
    }

}