package bohdan.webchat.login;

import bohdan.webchat.ConnectingStatus;

import java.io.Serializable;

/**
 * Created by Monteg on 22.03.2017.
 */
public class LoginResponse implements Serializable{
    private ConnectingStatus status;
    private int id;

    public LoginResponse() { }

    public ConnectingStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectingStatus status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status=" + status +
                ", id=" + id +
                '}';
    }
}
