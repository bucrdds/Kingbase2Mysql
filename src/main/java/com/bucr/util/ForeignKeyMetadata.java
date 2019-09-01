package com.bucr.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForeignKeyMetadata {
  private String pkTableName;
  private String pkColumnName;
  private String fkTableName;
  private String fkColumnName;
  private String fkName;
  private short keySequence;

  public ForeignKeyMetadata(ResultSet resultSet) throws SQLException {
    pkTableName = resultSet.getString("TABLE_NAME");
    pkColumnName = resultSet.getString("COLUMN_NAME");
    fkTableName = resultSet.getString("TABLE_NAME");
    fkColumnName = resultSet.getString("COLUMN_NAME");
    keySequence = resultSet.getShort("KEY_SEQ");
  }

  public String generateSql() {
    return "ALTER TABLE " + fkTableName
        + " ADD FOREIGN KEY  (" + fkColumnName + ") "
        + " REFERENCES " + pkTableName + "(" + pkColumnName +");";
  }

  public String getPkTableName() {
    return pkTableName;
  }

  public ForeignKeyMetadata setPkTableName(String pkTableName) {
    this.pkTableName = pkTableName;
    return this;
  }

  public String getPkColumnName() {
    return pkColumnName;
  }

  public ForeignKeyMetadata setPkColumnName(String pkColumnName) {
    this.pkColumnName = pkColumnName;
    return this;
  }

  public String getFkTableName() {
    return fkTableName;
  }

  public ForeignKeyMetadata setFkTableName(String fkTableName) {
    this.fkTableName = fkTableName;
    return this;
  }

  public String getFkColumnName() {
    return fkColumnName;
  }

  public ForeignKeyMetadata setFkColumnName(String fkColumnName) {
    this.fkColumnName = fkColumnName;
    return this;
  }

  public String getFkName() {
    return fkName;
  }

  public ForeignKeyMetadata setFkName(String fkName) {
    this.fkName = fkName;
    return this;
  }

  public short getKeySequence() {
    return keySequence;
  }

  public ForeignKeyMetadata setKeySequence(short keySequence) {
    this.keySequence = keySequence;
    return this;
  }
}
