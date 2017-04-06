package bohdan.webchat.messageBeans;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Monteg on 04.04.2017.
 */
public class MessagesList implements Serializable {
    private ArrayList<String> listOfMessages;

    public ArrayList<String> getListOfMessages() {
        return listOfMessages;
    }

    public void setListOfMessages(ArrayList<String> listOfMessages) {
        this.listOfMessages = listOfMessages;
    }
}
