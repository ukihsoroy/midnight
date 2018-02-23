package org.ko.prototype.generator.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

public class TableMetaData {

	private String columnName;
	
	private String comment;
	
	private String dataType;

	private boolean primaryKey;
	
	private Integer length;
	
	public String getFieldName(){
		String[] elements = columnName.split("_");
		String name = "";
		for(String e : elements){
			name += StringUtils.capitalize(e);
		}
		return StringUtils.uncapitalize(name);
	}
	
	public String getCommentHeader(){
		String header = columnName;
		
		if(comment != null && comment.trim().length() > 0){
			header = comment.trim().split("\\s+")[0].replaceAll(":", "");
		}
		
		return header;
	}
	
	public List<CommentElement> getCommentElements(){
		List<CommentElement> elements = new ArrayList<>();
		
		if(comment != null && comment.contains("#") && comment.trim().length() > 0){
			String[] parts = comment.split("\\s+");
			for(int i = 1; i < parts.length; i++){
				if(parts[i].contains("-") && parts[i].contains("#")){
					String value = parts[i].split("-")[0];
					String text = parts[i].split("-")[1].split("#")[0];
					CommentElement e = new CommentElement();
					e.setValue(value);
					e.setText(text);
					elements.add(e);
				}
			}
		}
		
		return elements;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public boolean isPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}
	
}
