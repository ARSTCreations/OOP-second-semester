package com.dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConn {
    public static Connection connectSQL() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException cnfE) {
            cnfE.printStackTrace();
        }
        Connection conn = null;
        String url = "jdbc:sqlite:dbBuku.sqlite";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println(conn);
        return conn;
    }
}
