package bohdan.webchat.entity;

/**
 * Created by Monteg on 15.03.2017.
 */
public class UsersEntity {
    private int id;
    private String userName;
    private String password;
    private String email;

    public UsersEntity() {
    }

    public UsersEntity(String userName) {
        this.userName = userName;
    }

    public UsersEntity(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public UsersEntity(int id, String userName, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UsersEntity{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}