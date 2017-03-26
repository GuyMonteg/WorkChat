package bohdan.webchat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Monteg on 21.03.2017.
 */
public class UserBean implements Serializable{
    private String name = null;
    private String password = null;
    private String email = null;

    public UserBean() { }

    public String getName() {
        return name;
    }

    public void setName(String nameS) {
        name = nameS;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordS) {
        password = passwordS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean myBeeans = (UserBean) o;
        return Objects.equals(name, myBeeans.name) &&
                Objects.equals(password, myBeeans.password) &&
                Objects.equals(email, myBeeans.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, password, email);
    }

}
