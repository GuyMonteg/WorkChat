package dbconnection;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by Monteg on 15.03.2017.
 */
public class MessagesTableData {
    private String author;
    private String messageText;
    private Date date;
    //private LocalTime time;
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

    /*public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }*/
}
