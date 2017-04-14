package bohdan.webchat.userBeans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Monteg on 07.04.2017.
 */
public class UserListBean implements Serializable {
    private ArrayList<UserBean> list;

    public UserListBean() { }

    public UserListBean(ArrayList<UserBean> list) {
        this.list = list;
    }

    public ArrayList<UserBean> getList() {
        return list;
    }

    public void setList(ArrayList<UserBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "list=" + list;
    }
}
