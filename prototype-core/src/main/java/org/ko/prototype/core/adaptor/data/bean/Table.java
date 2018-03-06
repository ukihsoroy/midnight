package org.ko.prototype.core.adaptor.data.bean;

import java.util.List;
import java.util.Optional;

public class Table extends BaseBean {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String name;

    private List<Column> columns;

    public Table(){}

    public Table(String name){
        this.name = name;
    }

    public Column getColumn(String name){
        Optional<Column> optional = columns.stream().filter(c -> c.getName().equalsIgnoreCase(name)).findFirst();
        return optional.isPresent() ? optional.get() : null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
