package rikkei.academy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String DIRVER_JBDC = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/product_spring_mvc";
    private static final String USER_JBDC ="root";
    private static final String PASSWORD_JBDC ="hoangutck57";

    public static Connection openConnection(){
        Connection connection = null ;
        try {
            Class.forName(DIRVER_JBDC);
            connection = DriverManager.getConnection(URL, USER_JBDC,PASSWORD_JBDC);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
          if (connection != null) {
              connection.close();
          }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
