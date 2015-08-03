package com.swell.tutorial.database.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class HsqlDemo {
  public static void main(String[] args) {
    try {
      Class.forName("org.hsqldb.jdbcDriver");

      // 服务器模式
      // Connection c =
      // DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/mydb", "sa", "");

      // 内存（Memory-Only）模式
      Connection c = DriverManager.getConnection("jdbc:hsqldb:mem:mydb", "sa", "");
      if (c != null) {
        System.out.println("Connected db success!");
        String sql = "CREATE TABLE TBL_USERS(ID INTEGER, NAME VARCHAR(50), BIRTHDAY DATE);";
        Statement st = c.createStatement();
        st.execute(sql);
        sql = "INSERT INTO TBL_USERS(ID, NAME, BIRTHDAY) VALUES ('1', 'ADMIN', SYSDATE);";
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
