package com.bucr.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

public class Kingbase2Mysql {


  public static void main(String[] args) throws SQLException, IOException {
    for(String arg : args){
      System.out.println(arg);
    }
    KingbaseConnector connector = new KingbaseConnector();
    List<TableMetaData> tables = connector.getTables();
    int count = 0;
    File createSqlFile = new File("./create.sql");
    if (!createSqlFile.exists()) {
      createSqlFile.createNewFile();
    }
    FileOutputStream createSqlOutStream = new FileOutputStream(createSqlFile);
    FileOutputStream foreignKeySqlOutStream = new FileOutputStream(createSqlFile, true);
    StringBuilder createSqlBuilder = new StringBuilder();
    StringBuilder foreignKeySqlBuilder = new StringBuilder();
    for (TableMetaData metaData : tables) {
      createSqlBuilder.append(metaData.generateSqlWithoutForeignKey());
      foreignKeySqlBuilder.append(metaData.generateForeignKeySql());
      ++count;
    }
    OutputStreamWriter outputStreamWriter =  new OutputStreamWriter(createSqlOutStream, StandardCharsets.UTF_8);
    outputStreamWriter.write(createSqlBuilder.toString());
    outputStreamWriter.close();
    outputStreamWriter = new OutputStreamWriter(foreignKeySqlOutStream, StandardCharsets.UTF_8);
    outputStreamWriter.write(foreignKeySqlBuilder.toString());
    outputStreamWriter.close();

    System.out.println(count);
  }
}
