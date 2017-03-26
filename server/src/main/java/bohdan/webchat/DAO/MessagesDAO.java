package bohdan.webchat.DAO;

import static bohdan.webchat.severControls.DBConnection.getDBConnections;
import bohdan.webchat.entity.MessagesEntity;
import java.util.List;
import java.sql.*;


/**
 * Created by Monteg on 24.03.2017.
 */
public class MessagesDAO {

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
        String z2 = "SELECT * FROM messages WHERE message_time = " + date;      //Дописать что бы последних 20 сообщ
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
}
