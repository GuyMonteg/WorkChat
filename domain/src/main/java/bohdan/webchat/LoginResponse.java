package bohdan.webchat;

import java.io.Serializable;

/**
 * Created by Monteg on 22.03.2017.
 */
public class LoginResponse implements Serializable{
    private ConnectingStatus status;

    public LoginResponse() { }

    public ConnectingStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "status=" + status +
                '}';
    }
}
