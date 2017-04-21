package bohdan.webchat.userBeans;

import bohdan.webchat.ConnectingStatus;

import java.io.Serializable;

/**
 * Created by Monteg on 20.04.2017.
 */
public class UserNotificationBean implements Serializable {
    private String name;
    private ConnectingStatus status;

    public UserNotificationBean() {
    }

    public UserNotificationBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ConnectingStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Name" + name + "\n" + "status" + status;
    }
}
