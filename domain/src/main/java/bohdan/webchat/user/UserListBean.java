package bohdan.webchat.user;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Monteg on 07.04.2017.
 */
public class UserListBean implements Serializable {
    private ArrayList<UserBean> list = new ArrayList<UserBean>();

    public UserListBean() { }

    public UserListBean(ArrayList<UserBean> list) {
        this.list = list;
    }

    public ArrayList<UserBean> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "list=" + list;
    }
}
