package org.ko.generator.constants

object SQLConstants {

  val INFORMATION_SCHEMA_TABLES = "SELECT table_name FROM information_schema.tables WHERE table_schema = ?"

  val INFORMATION_SCHEMA_COLUMNS = "SELECT * FROM information_schema.columns WHERE table_schema = ? AND table_name= ? and COLUMN_NAME not in ('version', 'enable', 'create_user', 'gmt_create', 'modified_user', 'gmt_modified')"

  val INFORMATION_SCHEMA_TABLE_DETAIL = "SELECT table_comment FROM information_schema.tables WHERE table_schema = ? and table_name = ?"

}
