package dbconnection;

import config.DBProperties;
import java.sql.*;

/**
 * Created by Monteg on 15.03.2017.
 */

public class DBConnection {

    public static Connection getDBConnections() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DBProperties.URL, DBProperties.USER, DBProperties.PASW);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void addNewUser(String name, String password, String email) {
        String inToDB = "INSERT INTO users VALUES (?, ?, ?)";
        Connection conn = getDBConnections();

        try (PreparedStatement ps = conn.prepareStatement(inToDB)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.executeUpdate();
            System.out.println("Insert of user is OK");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addMessages(String author, String message, Date date) {
        String inToDB = "INSERT INTO messages VALUES (?, ?, ?)";
        Connection conn = getDBConnections();

        try (PreparedStatement ps = conn.prepareStatement(inToDB)) {
            ps.setString(1, author);
            ps.setString(2, message);
            ps.setDate(3, date);
            ps.executeUpdate();
            System.out.println("Insert of message is OK");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getMessagesByAuthor(String author) {
        Connection conn = getDBConnections();
        String takeM = "SELECT * FROM messages WHERE author = " + author;
        ResultSet rs = null;

        try (PreparedStatement ps = conn.prepareStatement(takeM)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                String authorM = rs.getString("author");
                String  messageT = rs.getString("mesage_text");
                String time = rs.getString("message_time");
                System.out.println(authorM + " : " + messageT + "  " + time);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getMessagesByDate(Date date) {
        Connection conn = getDBConnections();
        String z2 = "SELECT * FROM messages WHERE message_time = " + date;      //Дописать что бы за последних 15 минут
        ResultSet rs = null;

        try (PreparedStatement ps = conn.prepareStatement(z2)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                String authorM = rs.getString("author");
                String  messageT = rs.getString("mesage_text");
                String time = rs.getString("message_time");
                System.out.println(authorM + " : " + messageT + "  " + time);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
