package bohdan.webchat.registrationnBeans;

import bohdan.webchat.ConnectingStatus;

import java.io.Serializable;

/**
 * Created by Monteg on 25.03.2017.
 */
public class RegistrationResponse implements Serializable{
    private ConnectingStatus status;

    public RegistrationResponse() { }

    public ConnectingStatus getStatus() {
        return status;
    }

    public void setStatus(ConnectingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RegistrationResponse{" +
                "status=" + status +
                '}';
    }
}
