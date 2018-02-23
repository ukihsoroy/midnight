package org.ko.prototype.generator.bean;

import org.apache.commons.lang3.StringUtils;

public class ColumnValue {

	private String type;
	
	private String name;
	
	private String comment;
	
	private String value;

	private String columnName;
	
	private String fieldName;
	
	public ColumnValue(){}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return format(name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return format(comment);
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getValue() {
		return format(value);
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	private String format(String text){
		String value = StringUtils.replace(text, "\\r", "");
		value = StringUtils.replace(value, "\\n", "");
		return value;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
}
