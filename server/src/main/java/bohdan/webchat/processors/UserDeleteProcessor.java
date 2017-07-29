package bohdan.webchat.processors;

import bohdan.webchat.entity.UsersEntity;
import bohdan.webchat.user.UserDelete;
import java.io.ObjectOutputStream;
import static bohdan.webchat.DAO.MessagesDAO.deleteMessagesByUser;
import static bohdan.webchat.DAO.UserDAO.deleteUserFromDB;

public class UserDeleteProcessor implements Processor {
    @Override
    public boolean canProcess(Object a) {
        return a instanceof UserDelete;
    }

    @Override
    public void process(Object a, ObjectOutputStream stream) {
        UserDelete delete = (UserDelete) a;
        UsersEntity entity = new UsersEntity();
        entity.setUserName(delete.getUsername());
        boolean result = deleteMessagesByUser(entity);
        if (result == true){
            deleteUserFromDB(entity);
        }
    }
}
