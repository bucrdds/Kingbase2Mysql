package com.bucr.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TableMetaData {
  private String tableCatalog;
  private String tableSchema;
  private String tableName;
  private String tableType;
  private String remarks;
  private String typeSchema;
  private String typeName;
  private String selfReferencingColName;
  private String refGeneration;
  private List<PrimaryKerMetaData> primaryKeys;
  private List<ColumnMetaData> columns;
  private List<ForeignKeyMetadata> foreignKeys;


  public TableMetaData(ResultSet resultSet) throws SQLException {
    tableCatalog = resultSet.getString("TABLE_CAT");
    tableSchema = resultSet.getString("TABLE_SCHEM");
    tableName = resultSet.getString("TABLE_NAME");
    tableType = resultSet.getString("TABLE_TYPE");
    remarks = resultSet.getString("REMARKS");
    typeSchema = resultSet.getString("TYPE_SCHEM");
    typeName = resultSet.getString("TYPE_NAME");
    selfReferencingColName = resultSet.getString("SELF_REFERENCING_COL_NAME");
    refGeneration = resultSet.getString("REF_GENERATION");
  }

  public String generateSqlWithoutForeignKey() {
    return ("CREATE TABLE `") + tableName + "`" + "("
        + generateColumns()
        + generatePrimaryKeys()
        + ");";
  }

  public String generateColumns() {
    if (columns == null || columns.size() == 0) {
      return "";
    }
    StringBuilder columnSqlBuilder = new StringBuilder();
    for (ColumnMetaData metaData : columns) {
      columnSqlBuilder.append(metaData.generateSql());
    }
    columnSqlBuilder.deleteCharAt(columnSqlBuilder.length() - 1);
    return columnSqlBuilder.toString();
  }

  public String generatePrimaryKeys() {
    if (primaryKeys == null || primaryKeys.size() == 0) {
      return "";
    }

    StringBuilder primaryKeySqlBuilder = new StringBuilder(",PRIMARY KEY (");
    for (PrimaryKerMetaData metaData : primaryKeys) {
      primaryKeySqlBuilder.append(metaData.getColumnName()).append(" ");
    }
    primaryKeySqlBuilder.append(")");
    return primaryKeySqlBuilder.toString();
  }

  public String generateForeignKeySql() {
    if (foreignKeys == null || foreignKeys.size() == 0) {
      return "";
    }

    StringBuilder foreignKeySqlBuilder = new StringBuilder();
    for (ForeignKeyMetadata metaData : foreignKeys) {
      foreignKeySqlBuilder.append(metaData.generateSql());
    }
    return foreignKeySqlBuilder.toString();
  }

  public String getTableCatalog() {
    return tableCatalog;
  }

  public void setTableCatalog(String tableCatalog) {
    this.tableCatalog = tableCatalog;
  }

  public String getTableSchema() {
    return tableSchema;
  }

  public void setTableSchema(String tableSchema) {
    this.tableSchema = tableSchema;
  }

  public String getTableName() {
    return tableName;
  }

  public void setTableName(String tableName) {
    this.tableName = tableName;
  }

  public String getTableType() {
    return tableType;
  }

  public void setTableType(String tableType) {
    this.tableType = tableType;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getTypeSchema() {
    return typeSchema;
  }

  public void setTypeSchema(String typeSchema) {
    this.typeSchema = typeSchema;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public String getSelfReferencingColName() {
    return selfReferencingColName;
  }

  public void setSelfReferencingColName(String selfReferencingColName) {
    this.selfReferencingColName = selfReferencingColName;
  }

  public String getRefGeneration() {
    return refGeneration;
  }

  public void setRefGeneration(String refGeneration) {
    this.refGeneration = refGeneration;
  }

  public List<PrimaryKerMetaData> getPrimaryKeys() {
    return primaryKeys;
  }

  public void setPrimaryKeys(List<PrimaryKerMetaData> primaryKeys) {
    this.primaryKeys = primaryKeys;
  }

  public List<ColumnMetaData> getColumns() {
    return columns;
  }

  public void setColumns(List<ColumnMetaData> columns) {
    this.columns = columns;
  }

  public List<ForeignKeyMetadata> getForeignKeys() {
    return foreignKeys;
  }

  public TableMetaData setForeignKeys(List<ForeignKeyMetadata> foreignKeys) {
    this.foreignKeys = foreignKeys;
    return this;
  }

  @Override
  public String toString() {
    return "TableMetaData{" +
        "tableCatalog='" + tableCatalog + '\'' +
        ", tableSchema='" + tableSchema + '\'' +
        ", tableName='" + tableName + '\'' +
        ", tableType='" + tableType + '\'' +
        ", remarks='" + remarks + '\'' +
        ", typeSchema='" + typeSchema + '\'' +
        ", typeName='" + typeName + '\'' +
        ", selfReferencingColName='" + selfReferencingColName + '\'' +
        ", refGeneration='" + refGeneration + '\'' +
        '}';
  }
}
