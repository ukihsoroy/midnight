package org.ko.prototype.data.master.dao.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.Map.Entry;

//@Service
public class TableMetaDataRepository //implements ITableMetaDataRepository
{

//	private static final Logger log = LoggerFactory.getLogger(TableMetaDataRepository.class);
//
//	private DataSource dataSource;
//
//	private String dbname;
//
//	private List<Table> tables = new ArrayList<>();
//
//	@PostConstruct public void init() throws Exception {
//		Connection conn = dataSource.getConnection();
//
//		String sql = "select * from information_schema.columns where table_schema = '" + dbname + "' order by table_name";
//		log.debug("execute sql {}", sql);
//
//		PreparedStatement stmt = conn.prepareStatement(sql);
//		ResultSet rs = stmt.executeQuery();
//		Map<String, List<Column>> tableMap = new HashMap<>();
//		while(rs.next()){
//			String name = rs.getString("TABLE_NAME");
//
//			List<Column> columns = tableMap.get(name);
//			if(columns == null){
//				columns = new ArrayList<>();
//				tableMap.put(name, columns);
//			}
//
//			Column column = new Column();
//			column.setName(rs.getString("COLUMN_NAME"));
//			column.setType(rs.getString("DATA_TYPE"));
//			column.setComment(rs.getString("COLUMN_COMMENT"));
//			if("PRI".equalsIgnoreCase(rs.getString("COLUMN_KEY"))){
//				column.setPrimaryKey(true);
//			}
//			if("auto_increment".equalsIgnoreCase(rs.getString("EXTRA"))){
//				column.setAutoIncrement(true);
//			}
//
//			columns.add(column);
//		}
//
//		rs.close();
//		stmt.close();
//
//		Set<Entry<String, List<Column>>> entries = tableMap.entrySet();
//		for(Entry<String, List<Column>> entry : entries){
//			Table table = new Table(entry.getKey());
//			table.setColumns(entry.getValue());
//			tables.add(table);
//		}
//	}
//
////	@Override
////	public boolean containsColumn(String tableName, String columnName) {
////		tables.stream().forEach(t -> t.getName().equalsIgnoreCase(tableName));
////		return false;
////	}
//
//	public List<Table> getTables() {
//		return tables;
//	}
//
//	public DataSource getDataSource() {
//		return dataSource;
//	}
//
//	public void setDataSource(DataSource dataSource) {
//		this.dataSource = dataSource;
//	}
//
//	public String getDbname() {
//		return dbname;
//	}
//
//	public void setDbname(String dbname) {
//		this.dbname = dbname;
//	}

}
