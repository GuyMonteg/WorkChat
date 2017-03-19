package dbconnection;

import java.sql.Date;

/**
 * Created by Monteg on 15.03.2017.
 */

public class MessagesTableEntity {
    private String author;
    private String messageText;
    private Date date;                  //this Date from java.sql.Date
    //private LocalDateTime dateTime;       Maybe will be better to use this class


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

}
