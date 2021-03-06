package bohdan.webchat.DAO;

import static bohdan.webchat.DAO.DBConnection.getDBConnections;

import bohdan.webchat.entity.MessagesEntity;
import bohdan.webchat.entity.UsersEntity;
import bohdan.webchat.message.MessageBean;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;


/**
 * Created by Monteg on 24.03.2017.
 */
public class MessagesDAO {

    public static void addMessages(MessageBean mtd) {
        String inToDB = "INSERT INTO messages VALUES (?, ?, ?, ?)";
        Connection conn = getDBConnections();

        try (PreparedStatement ps = conn.prepareStatement(inToDB)) {
            ps.setInt(1, mtd.getId());
            ps.setString(2, mtd.getAuthor());
            ps.setString(3, mtd.getMessageText());
            ps.setString(4, mtd.getDate());
            ps.executeUpdate();
            System.out.println("Insert of message is OK");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
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
        }
        return null;
    }

    public static ArrayList<MessagesEntity> getMessagesByDate() {
        ArrayList<MessagesEntity> messageList = new ArrayList<>();
        Connection conn = getDBConnections();
        String z2 = "SELECT * FROM messages ORDER BY message_time LIMIT 20";
        ResultSet rs = null;

        try (PreparedStatement ps = conn.prepareStatement(z2)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("m_id");
                String authorM = rs.getString("author");
                String  messageT = rs.getString("mesage_text");
                String time = rs.getString("message_time");
                messageList.add(new MessagesEntity(authorM, messageT, time));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return messageList;
    }

    public static boolean deleteMessagesByUser(UsersEntity name) {
        boolean result = false;
        String inToDB = "DELETE FROM messages WHERE author = ?;";
        Connection conn = getDBConnections();

        try(PreparedStatement ps = conn.prepareStatement(inToDB)) {
            ps.setString(1, name.getUserName());
            ps.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
}
