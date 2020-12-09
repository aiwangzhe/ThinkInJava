package jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnToMysql {
    private static final String JDBC_URL = "";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "bigdata";

    public static void main(String[] args) {
        try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        conn.prepareStatement()
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
