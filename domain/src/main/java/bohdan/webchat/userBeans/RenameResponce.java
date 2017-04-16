package bohdan.webchat.userBeans;

import bohdan.webchat.ConnectingStatus;

import java.io.Serializable;

/**
 * Created by Monteg on 16.04.2017.
 */
public class RenameResponce implements Serializable{
    private ConnectingStatus status;

    public RenameResponce() { }

    public RenameResponce(ConnectingStatus status) {
        this.status = status;
    }

    public ConnectingStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RenameResponce{" +
                "status=" + status +
                '}';
    }
}
