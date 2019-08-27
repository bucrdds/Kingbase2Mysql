package com.bucr.util;

import java.sql.Connection;

public class MysqlConnector {
  private static final String DB_PREFIX = "jdbc:mysql://";
  private String user = "root";
  private String password = "123456";
  private String url;
  private Connection conn = null;

  public MysqlConnector(String hostname, String port, String database, String user, String password){
    url = DB_PREFIX
        + (hostname == null ? "localhost" : hostname) + ":"
        + (port == null ? "3306" : port) + "/"
        + (database == null ? "" : database);

  }
}
