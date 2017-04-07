package bohdan.webchat.messageBeans;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Monteg on 31.03.2017.
 */

public class MessageBean implements Serializable {
    private String author;
    private String messageText;
    private Date date;

    public MessageBean() {}

    public MessageBean(String author, String messageText, Date date) {
        this.author = author;
        this.messageText = messageText;
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return author + ": " + " " + messageText + "\n" + date;
    }
}
