package connection;

import java.sql.*;

/**
 * Manages the database connection using JDBC.
 */

public class ConnectionFactory {
    private static final String DRIVER_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/orders_management";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static ConnectionFactory singleInstance = new ConnectionFactory();
    private Connection connection;

    private ConnectionFactory() {
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a single shared connection to the database.
     * Reconnects if the existing connection is closed or null.
     */
    public static Connection getConnection() {
        try {
            if (singleInstance.connection == null || singleInstance.connection.isClosed()) {
                singleInstance.connection = DriverManager.getConnection(DBURL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return singleInstance.connection;
    }
}
