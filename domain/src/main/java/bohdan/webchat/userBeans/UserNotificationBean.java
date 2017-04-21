package bohdan.webchat.userBeans;

import java.io.Serializable;

/**
 * Created by Monteg on 20.04.2017.
 */
public class UserNotificationBean implements Serializable {
    private String connectionStatus;

    public UserNotificationBean() {
    }

    public UserNotificationBean(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    public void setConnectionStatus(String connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    @Override
    public String toString() {
        return connectionStatus;
    }
}
