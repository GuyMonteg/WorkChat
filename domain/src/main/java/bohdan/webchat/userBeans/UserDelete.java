package bohdan.webchat.userBeans;

import java.io.Serializable;

/**
 * Created by Monteg on 16.04.2017.
 */
public class UserDelete implements Serializable{
    private String username;

    public UserDelete() { }

    public UserDelete(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserDelete{" +
                "username='" + username + '\'' +
                '}';
    }
}
