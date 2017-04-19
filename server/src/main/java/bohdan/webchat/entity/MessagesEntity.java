package bohdan.webchat.entity;

import java.sql.Date;

/**
 * Created by Monteg on 15.03.2017.
 */

public class MessagesEntity {
    private int m_id;
    private String author;
    private String messageText;
    private String date;

    public MessagesEntity() {}

    public MessagesEntity(String author, String messageText, String date) {
        this.author = author;
        this.messageText = messageText;
        this.date = date;
    }

    public int getM_id() {
        return m_id;
    }

    public void setM_id(int m_id) {
        this.m_id = m_id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
