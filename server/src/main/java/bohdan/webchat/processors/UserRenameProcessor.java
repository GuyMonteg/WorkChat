package bohdan.webchat.processors;

import bohdan.webchat.ConnectingStatus;
import bohdan.webchat.user.RenameResponce;
import bohdan.webchat.user.UserRename;

import java.io.IOException;
import java.io.ObjectOutputStream;

import static bohdan.webchat.DAO.UserDAO.userRename;

public class UserRenameProcessor implements Processor {

    @Override
    public boolean canProcess(Object a) {
        return a instanceof UserRename;
    }

    @Override
    public void process(Object a, ObjectOutputStream stream) {
        RenameResponce renameResponce = new RenameResponce();
        UserRename rename = (UserRename) a;
        boolean verify = userRename(rename);
        if (verify == true) {
            renameResponce.setNewName(rename.getNewName());
            renameResponce.setStatus(ConnectingStatus.OK);
        } else {
            renameResponce.setStatus(ConnectingStatus.FAIL);
        }
        try {
            stream.writeObject(renameResponce);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
