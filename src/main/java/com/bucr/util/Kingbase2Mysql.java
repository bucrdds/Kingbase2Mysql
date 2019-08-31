package com.bucr.util;

import java.sql.SQLException;
import java.util.List;

public class Kingbase2Mysql {


  public static void main(String[] args) throws SQLException {
    for(String arg : args){
      System.out.println(arg);
    }
    KingbaseConnector connector = new KingbaseConnector();
    connector.getTables();
  }
}
