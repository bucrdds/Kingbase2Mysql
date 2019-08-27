package com.bucr.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class KingbaseConnector {
  private static final String DB_PREFIX = "jdbc:kingbase://";
  private Connection conn = null;
  private String url;
  private String user;
  private String password;
  public KingbaseConnector(String host, String port, String database, String user, String password){
    url = DB_PREFIX + host + ":" + port + "/" + database;
    this.user = user;
    this.password = password;
  }

  public Connection getConn() {
    try {
      Class.forName("com.kingbase.Driver");
      conn = DriverManager.getConnection(url, user, password);
      if(conn != null) {
        return conn;
      } else {
        System.out.println(this.getClass().getName() + ": Get connection FAILED! Connection is NULL!");
      }
    } catch (ClassNotFoundException e) {
      System.out.println(this.getClass().getName() + ": Cannot found \"com.kingbase.Driver\". Exception need to handle.");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println(this.getClass().getName() + ": Get connection FAILED. Exception need to handle.");
      e.printStackTrace();
    }
  }

  public List<String> getTablesName(){

  }
}
