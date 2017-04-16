package bohdan.webchat.DAO;

import static bohdan.webchat.severControls.DBConnection.getDBConnections;
import bohdan.webchat.entity.UsersEntity;
import bohdan.webchat.userBeans.UserBean;
import bohdan.webchat.userBeans.UserDelete;
import bohdan.webchat.userBeans.UserRename;

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

    public static ArrayList<UsersEntity> getUsersList() {
        ArrayList<UsersEntity> list = new ArrayList<>();
        Connection conn = getDBConnections();
        String takeUsers = "SELECT user_name FROM users";
        ResultSet rs = null;

        try (PreparedStatement ps = conn.prepareStatement(takeUsers)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                String userName = rs.getString("user_name");
                list.add(new UsersEntity(userName));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public static boolean userRename(UserRename rename) {
        String inToDb = "UPDATE users SET user_name = ?, password = ? WHERE user_name = ?;";
        //String inToDb2 = "UPDATE messages SET author = ? WHERE author = ?;";
        Connection dbConn = getDBConnections();
        boolean result = false;
        try (PreparedStatement ps = dbConn.prepareStatement(inToDb) /*;
             PreparedStatement ps2 = dbConn.prepareStatement(inToDb2)*/) {
            ps.setString(1, rename.getNewName());
            ps.setString(2, rename.getNewPass());
            ps.setString(3, rename.getOldName());
            ps.executeUpdate();
            result = true;
            /*ps2.setString(1, rename.getNewName());        //нужно ли обновлять значения во второй таблице если они
            ps2.setString(2, rename.getOldName());         //привязаны с помощью primary key?
            ps2.executeUpdate();*/
        } catch (SQLException e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public static void deleteFromDB(UsersEntity name) {
        String inToDB = "DELETE FROM users WHERE user_name = ?;"; // нужно ли после делейт *,
        Connection dbConn = getDBConnections();                  // удалять ли и сообщения этого пользователя
        try (PreparedStatement ps = dbConn.prepareStatement(inToDB)) {
            ps.setString(1, name.getUserName());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
