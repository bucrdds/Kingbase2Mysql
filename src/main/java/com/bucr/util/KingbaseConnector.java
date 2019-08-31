package com.bucr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KingbaseConnector {

  private static String DB_PREFIX = "jdbc:kingbase://";

  static {
    try {
      Class.forName("com.kingbase.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private String url;
  private String hostname = "localhost";
  private String port = "54321";
  private String user = "SYSTEM";
  private String password = "EZDB";

  public KingbaseConnector() {
    url = DB_PREFIX + hostname + ":" + port + "/GFJY";
  }
  
  public List<TableMetaData> getTables() throws SQLException {
    Connection connection = getConnection();
    ArrayList<TableMetaData> tables = new ArrayList<>();
    ResultSet rs = connection.getMetaData().getTables(
        null,
        "EZDB",
        null,
        new String[]{"TABLE"});
    while (rs.next()) {
      TableMetaData table = new TableMetaData(rs);
      table.setColumns(getColumns(table.getTableName()));
      tables.add(table);
    }
    System.out.println(tables);
    closeConnection(connection);
    return tables;
  }

  public List<ColumnMetaData> getColumns(String tableName) throws SQLException {
    Connection connection = getConnection();
    List<ColumnMetaData> columns = new ArrayList<>();
    ResultSet rs = connection.getMetaData().getColumns(
        null,
        null,
        tableName,
        null);
    while (rs.next()) {
      columns.add(new ColumnMetaData(rs));
    }
    closeConnection(connection);
    return columns;
  }

  private Connection getConnection() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }

  private void closeConnectionAndStatement(Connection connection, Statement statement) {
    closeConnection(connection);
    closeStatement(statement);
  }

  private void closeConnection(Connection connection) {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  private void closeStatement(Statement statement) {
    try {
      if (statement != null) {
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}