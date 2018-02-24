package org.ko.prototype.generator.bean

import org.apache.commons.lang3.StringUtils

class ColumnValue {

    String type

    String name

    String comment

    String value

    String columnName

    String fieldName

    private String format(String text){
        String value = StringUtils.replace(text, "\\r", "")
        StringUtils.replace(value, "\\n", "")
    }

}
