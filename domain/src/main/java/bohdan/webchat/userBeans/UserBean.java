package bohdan.webchat.userBeans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Monteg on 07.04.2017.
 */
public class UserBean implements Serializable {
    private ArrayList<String> list;

    public UserBean() { }

    public UserBean(ArrayList<String> list) {
        this.list = list;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "list=" + list;
    }
}
