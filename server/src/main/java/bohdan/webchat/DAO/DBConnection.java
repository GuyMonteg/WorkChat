package bohdan.webchat.DAO;

import bohdan.webchat.config.DBProperties;
import java.sql.*;

/**
 * Created by Monteg on 15.03.2017.
 */

public class DBConnection {
    private static Connection connection;

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(DBConnection::closeConnection));
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getDBConnections() {
        /*try { Class.forName("com.mysql.jdbc.Driver");} catch (ClassNotFoundException ex) {ex.printStackTrace();}*/
        try {
            if (connection == null || connection.isClosed()) {
                return connection = DriverManager.getConnection(DBProperties.URL,
                        DBProperties.USER, DBProperties.PASW);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
