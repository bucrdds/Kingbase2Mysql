package com.bucr.util;

import java.sql.ResultSet;
import java.sql.SQLException;

class PrimaryKerMetaData {
  private String tableName;
  private String columnName;
  private short keySequence;

  public PrimaryKerMetaData(ResultSet resultSet) throws SQLException {
    tableName = resultSet.getString("TABLE_NAME");
    columnName = resultSet.getString("COLUMN_NAME");
    keySequence = resultSet.getShort("KEY_SEQ");
  }

  public String getTableName() {
    return tableName;
  }

  public PrimaryKerMetaData setTableName(String tableName) {
    this.tableName = tableName;
    return this;
  }

  public String getColumnName() {
    return columnName;
  }

  public PrimaryKerMetaData setColumnName(String columnName) {
    this.columnName = columnName;
    return this;
  }

  public short getKeySequence() {
    return keySequence;
  }

  public PrimaryKerMetaData setKeySequence(short keySequence) {
    this.keySequence = keySequence;
    return this;
  }
}
