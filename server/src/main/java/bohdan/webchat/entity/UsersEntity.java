package bohdan.webchat.entity;

import bohdan.webchat.UserBean;
import bohdan.webchat.severControls.DBConnection;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Created by Monteg on 15.03.2017.
 */
public class UsersEntity implements PropertyChangeListener {
    private String userName;
    private String password;
    private String email;

    public UsersEntity() {
    }

    public UsersEntity(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UsersEntity(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}


















/*@Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || o.getClass() != this.getClass()) return false;
        UsersEntity guest = (UsersEntity) o;
        return userName == guest.userName || (userName != null && userName.equals(guest.getUserName())
                || password != null && password.equals(guest.getPassword()));
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        return result;
    }*/

