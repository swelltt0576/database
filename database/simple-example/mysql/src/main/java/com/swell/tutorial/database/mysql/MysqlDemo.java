package com.swell.tutorial.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MysqlDemo {
  public static void main(String[] args) {
    try {
      String driverClass = "com.mysql.jdbc.Driver";
      String url = "jdbc:mysql://localhost:3306/jpatest?characterEncoding=UTF-8";
      String user = "jpatest";
      String password = "jpatest";

      Class.forName(driverClass);
      Connection c = DriverManager.getConnection(url, user, password);
      if (c != null) {
        System.out.println("Connected db success!");
        String sql = "CREATE TABLE TBL_USERS(ID INTEGER, NAME VARCHAR(50), BIRTHDAY DATE);";
        Statement st = c.createStatement();
        st.execute(sql);
        sql = "INSERT INTO TBL_USERS(ID, NAME, BIRTHDAY) VALUES ('1', 'ADMIN', SYSDATE());";
        st.executeUpdate(sql);
        if (st != null) {
          st.close();
        }
        c.close();
      }
    } catch (Exception e) {
      System.out.println("ERROR:failed to load HSQLDB JDBC driver.");
      e.printStackTrace();
      return;
    }
  }
}
