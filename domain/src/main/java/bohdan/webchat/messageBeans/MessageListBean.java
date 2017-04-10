package bohdan.webchat.messageBeans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Monteg on 10.04.2017.
 */
public class MessageListBean implements Serializable {
    private ArrayList<MessageBean> list;

    public MessageListBean() {
    }

    public MessageListBean(ArrayList<MessageBean> list) {
        this.list = list;
    }

    public ArrayList<MessageBean> getList() {
        return list;
    }

    public void setList(ArrayList<MessageBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "list=" + list;
    }
}
