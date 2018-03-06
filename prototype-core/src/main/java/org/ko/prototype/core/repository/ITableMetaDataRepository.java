package org.ko.prototype.core.repository;

import org.ko.prototype.core.adaptor.data.bean.Table;

import java.util.List;

public interface ITableMetaDataRepository {

    List<Table> getTables();

    boolean containsColumn(String tableName, String columnName);
}
