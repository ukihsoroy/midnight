package org.ko.prototype.generator.bean;

import java.util.List;

public class QueryElement {

	private String text;
	
	private String columnName;
	
	private String elementType;

	private List<SelectOption> options;
	
	private Integer length;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public List<SelectOption> getOptions() {
		return options;
	}

	public void setOptions(List<SelectOption> options) {
		this.options = options;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

}
