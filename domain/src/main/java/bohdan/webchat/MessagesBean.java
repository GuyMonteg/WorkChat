package bohdan.webchat;

import java.sql.Date;
import java.util.Objects;

/**
 * Created by Monteg on 21.03.2017.
 */
public class MessagesBean {
    private String author;
    private String messageText;
    private Date date;

    public MessagesBean() {
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
        return "Author " + author + '\'' +
                ", messageText '" + messageText + '\'' +
                "', date" + date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessagesBean messagesBeans = (MessagesBean) o;
        return Objects.equals(author, messagesBeans.author) &&
                Objects.equals(messageText, messagesBeans.messageText) &&
                Objects.equals(date, messagesBeans.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, messageText, date);
    }
}
