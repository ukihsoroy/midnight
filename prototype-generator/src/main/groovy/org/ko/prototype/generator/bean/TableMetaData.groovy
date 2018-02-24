package org.ko.prototype.generator.bean

import org.springframework.util.StringUtils

class TableMetaData {

    String columnName

    String comment

    String dataType

    boolean primaryKey

    Integer length

    String getFieldName(){
        String[] elements = columnName.split("_")
        String name = ""
        for(String e : elements){
            name += StringUtils.capitalize(e)
        }
        StringUtils.uncapitalize(name)
    }

    String getCommentHeader(){
        String header = columnName
        if(comment != null && comment.trim().length() > 0){
            header = comment.trim().split("\\s+")[0].replaceAll(":", "")
        }
        return header
    }

    List<CommentElement> getCommentElements(){
        List<CommentElement> elements = new ArrayList<>()

        if(comment != null && comment.contains("#") && comment.trim().length() > 0){
            String[] parts = comment.split("\\s+")
            for(int i = 1; i < parts.length; i++){
                if(parts[i].contains("-") && parts[i].contains("#")){
                    String value = parts[i].split("-")[0]
                    String text = parts[i].split("-")[1].split("#")[0]
                    CommentElement e = new CommentElement()
                    e.setValue(value)
                    e.setText(text)
                    elements.add(e)
                }
            }
        }
        elements
    }

}
