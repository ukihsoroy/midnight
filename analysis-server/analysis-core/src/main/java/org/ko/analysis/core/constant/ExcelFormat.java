package org.ko.analysis.core.constant;

/**
 * @author kundy
 * @create 2019/2/15 11:54 AM
 */
public enum ExcelFormat {

    FORMAT_INTEGER("INTEGER"),
    FORMAT_DOUBLE("DOUBLE"),
    FORMAT_LONG("LONG"),
    FORMAT_PERCENT("PERCENT"),
    FORMAT_DATE("DATE"),
    FORMAT_VARCHAR("VARCHAR");

    private String value;

    ExcelFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
