package bohdan.webchat.user;

import java.io.Serializable;

/**
 * Created by Monteg on 16.04.2017.
 */
public class UserRename implements Serializable {
    private String oldName;
    private String newName;
    private String newPass;

    public UserRename() { }

    public UserRename(String oldName, String newName, String newPass) {
        this.oldName = oldName;
        this.newName = newName;
        this.newPass = newPass;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    @Override
    public String toString() {
        return "UserRename{" +
                "oldName='" + oldName + '\'' +
                ", newName='" + newName + '\'' +
                ", newPass='" + newPass + '\'' +
                '}';
    }
}
