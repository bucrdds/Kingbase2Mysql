package com.bucr.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlConnector {

  private static final String DB_PREFIX = "jdbc:mysql://";

  static {
    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private String url;
  private String user;
  private String password;

  private MysqlConnector(Builder builder){
    url = builder.url;
    user = builder.user;
    password = builder.password;
  }

  public void createDatabase(String database) {
    String sql = "CREATE DATABASE ?";
    Connection conn = getConnection();
    PreparedStatement ps = null;
    try {
      ps = conn.prepareStatement(sql);
      ps.setString(1, database);
      ps.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeConnectionAndStatement(conn, ps);
    }
  }

  public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern,
      String[] types) throws SQLException {
    return getConnection().getMetaData().getTables(catalog, schemaPattern, tableNamePattern, types);
  }

  public Connection getConnection() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(url, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }

  public void closeConnectionAndStatement(Connection connection, Statement statement) {
    closeConnection(connection);
    closeStatement(statement);
  }

  public void closeConnection(Connection connection) {
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void closeStatement(Statement statement) {
    try {
      if (statement != null) {
        statement.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static class Builder {

    private String url = "localhost";
    private String hostname = "localhost";
    private String port = "3306";
    private String database = "";
    private String user = "root";
    private String password = "krms";

    public Builder hostname(String hostname) {
      this.hostname = hostname;
      return this;
    }

    public Builder port(String port) {
      this.port = port;
      return this;
    }

    public Builder databse(String database) {
      this.database = database;
      return this;
    }

    public Builder user(String user) {
      this.user = user;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    private String url() {
      return DB_PREFIX + hostname + ":" + port + "/" + database;
    }

    public MysqlConnector build() {
      url = url();
      return new MysqlConnector(this);
    }
  }
}
