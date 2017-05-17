package bohdan.webchat.message;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Monteg on 10.04.2017.
 */
public class MessageListBean implements Serializable {
    private ArrayList<MessageBean> list = new ArrayList<MessageBean>();

    public MessageListBean() {
    }

    public MessageListBean(ArrayList<MessageBean> list) {
        this.list = list;
    }

    public ArrayList<MessageBean> getList() {
        return list;
    }

    @Override
    public String toString() {
        return "list=" + list;
    }
}
