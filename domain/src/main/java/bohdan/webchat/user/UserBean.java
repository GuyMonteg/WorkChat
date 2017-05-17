package bohdan.webchat.user;

import java.io.Serializable;

/**
 * Created by Monteg on 14.04.2017.
 */
public class UserBean implements Serializable {
    private String username;

    public UserBean() {
    }

    public UserBean(String username) {
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
        return username;
    }
}
