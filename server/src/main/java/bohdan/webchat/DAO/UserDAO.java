package bohdan.webchat.DAO;

import static bohdan.webchat.severControls.DBConnection.getDBConnections;
import bohdan.webchat.entity.UsersEntity;
import bohdan.webchat.userBeans.UserBean;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by Monteg on 24.03.2017.
 */

public class UserDAO {

    public static UsersEntity findUserByName(String name) {
        String inToDB = "SELECT * FROM users WHERE user_name = ?;";
        Connection conn = getDBConnections();
        try (PreparedStatement ps = conn.prepareStatement(inToDB)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                UsersEntity ue = new UsersEntity();
                ue.setUserName(rs.getString(1));
                ue.setPassword(rs.getString(2));
                ue.setEmail(rs.getString(3));
                return ue;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
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
        }
        return result;
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
        }
    }

    public static UserBean getUsersList() {
        ArrayList<String> list = new ArrayList<>();
        UserBean userBean = new UserBean();
        Connection conn = getDBConnections();
        String takeUsers = "SELECT user_name FROM users";
        ResultSet rs = null;

        try (PreparedStatement ps = conn.prepareStatement(takeUsers)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("user_name");
                list.add(userName);
            }
            userBean.setList(list);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userBean;
    }
}
