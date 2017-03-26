package bohdan.webchat.entity;

import java.sql.Date;

/**
 * Created by Monteg on 15.03.2017.
 */

public class MessagesEntity {
    private String author;
    private String messageText;
    private Date date;
    //private LocalDateTime date;

    public MessagesEntity() {}

    public MessagesEntity(String author, String messageText, Date date) {
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
        return "MessagesEntity{" +
                "author='" + author + '\'' +
                ", messageText='" + messageText + '\'' +
                ", date=" + date +
                '}';
    }
}
