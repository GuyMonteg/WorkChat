package severControls;

import config.DBProperties;
import entity.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public static void addNewUser(UsersEntity utd) {
        String inToDB = "INSERT INTO users VALUES (?, ?, ?)";
        Connection conn = getDBConnections();

        try (PreparedStatement ps = conn.prepareStatement(inToDB)) {
            ps.setString(1, utd.getUserName());
            ps.setString(2, utd.getPassword());
            ps.setString(3, utd.getEmail());
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

    public static boolean loginUser(String name, String password) {
        String inToDB = "SELECT user_name, password FROM users WHERE user_name = ? AND password = ?;";
        Connection conn = getDBConnections();
        boolean result = false;
        try (PreparedStatement ps = conn.prepareStatement(inToDB)) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            result = rs.next();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean userRegisterControl(String name) {
        String inToDB = "SELECT user_name, password FROM users WHERE user_name = ?;";
        Connection conn = getDBConnections();
        boolean result = false;
        try (PreparedStatement ps = conn.prepareStatement(inToDB)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            result = rs.next();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static void addMessages(MessagesEntity mtd) {
        String inToDB = "INSERT INTO messages VALUES (?, ?, ?)";
        Connection conn = getDBConnections();

        try (PreparedStatement ps = conn.prepareStatement(inToDB)) {
            ps.setString(1, mtd.getAuthor());
            ps.setString(2, mtd.getMessageText());
            ps.setDate(3, mtd.getDate());
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

    public List<String> getMessagesByAuthor(String author) {        // в листе не стринг должен быть
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
        return null;        // need not null
    }

    public List<String> getMessagesByDate(Date date) {      // в листе не стринг должен быть
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
        return null;  // need not null
    }

    public static ArrayList<String> getUsersList() {
        ArrayList<String> list = new ArrayList<>();

        Connection conn = getDBConnections();
        String takeUsers = "SELECT user_name FROM users";
        ResultSet rs = null;

        try (PreparedStatement ps = conn.prepareStatement(takeUsers)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("user_name");
                list.add(userName);
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
        return list;
    }
}
