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
    private boolean isExist = false;
    private PropertyChangeSupport pcs;

    public UserBean() {
        pcs = new PropertyChangeSupport(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String nameS) {
        pcs.firePropertyChange("name", name, nameS);
        name = nameS;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordS) {
        pcs.firePropertyChange("password", password, passwordS);
        password = passwordS;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isExist() {
        return isExist;
    }

    public void setExist(boolean exist) {
        isExist = exist;
    }

    @Override
    public String toString() {
        return "User name is " + name + '\'' +
                ", password is " + password + '\'' +
                ", and email is " + email + '\'';
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

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        pcs.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        pcs.removePropertyChangeListener(pcl);
    }


}
