package bohdan.webchat.messageBeans;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Monteg on 02.04.2017.
 */
public class MessageResponse implements Serializable{
    private String author;
    private String messageText;
    private Date date;

    public MessageResponse() { }

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
