package bohdan.webchat;

import java.io.Serializable;

/**
 * Created by Monteg on 23.03.2017.
 */
public class LoginRequest implements Serializable{
    private String username;
    private String userpassword;

    public LoginRequest() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String password) {
        this.userpassword = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
                "username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                '}';
    }
}
