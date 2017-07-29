package bohdan.webchat.registrationn;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Monteg on 24.03.2017.
 */
public class RegistrationRequest implements Serializable{
    private String username;
    private String password;
    private String email;

    public RegistrationRequest() { }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    public String toString() {
        return "RegistrationResponse{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
