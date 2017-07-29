package bohdan.webchat.processors;

import bohdan.webchat.ConnectingStatus;
import bohdan.webchat.entity.MessagesEntity;
import bohdan.webchat.entity.UsersEntity;
import bohdan.webchat.login.LoginRequest;
import bohdan.webchat.login.LoginResponse;
import bohdan.webchat.message.MessageBean;
import bohdan.webchat.message.MessageListBean;
import bohdan.webchat.user.UserBean;
import bohdan.webchat.user.UserListBean;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import static bohdan.webchat.DAO.MessagesDAO.getMessagesByDate;
import static bohdan.webchat.DAO.UserDAO.findUserByName;
import static bohdan.webchat.DAO.UserDAO.getUsersList;

/**
 * Created by Monteg on 16.07.2017.
 */

public class LoginRequestProcessor implements Processor {

    @Override
    public boolean canProcess(Object a) {
        return a instanceof LoginRequest;
    }

    @Override
    public void process(Object a, ObjectOutputStream stream) {
        LoginResponse loginResponse = new LoginResponse();
        LoginRequest loginRequest = (LoginRequest) a;
        System.out.println(loginRequest.toString());
        UsersEntity user = findUserByName(loginRequest.getUsername());
        if (user != null) {
            if (user.getPassword().equals(loginRequest.getUserpassword())) {
                loginResponse.setId(user.getId());
                loginResponse.setStatus(ConnectingStatus.OK);
                System.out.println(loginResponse.getStatus());
            } else {
                loginResponse.setStatus(ConnectingStatus.WRONGPASS);
            }
        } else {
            loginResponse.setStatus(ConnectingStatus.WRONGNAME);
        }
        try {
            stream.writeObject(loginResponse);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (loginResponse.getStatus().equals(ConnectingStatus.OK)) {
            viewMessagesList(stream);
            viewUsersList(stream);
        }
    }

    private void viewMessagesList(ObjectOutputStream stream) {
        ArrayList<MessagesEntity> list = getMessagesByDate();
        MessageListBean listbean = new MessageListBean();
        for (MessagesEntity me : list) {
            listbean.getList().add(new MessageBean(me.getAuthor(), me.getMessageText(), me.getDate()));
        }
        try {
            stream.writeObject(listbean);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewUsersList(ObjectOutputStream stream) {
        ArrayList<UsersEntity> list = getUsersList();
        UserListBean uList = new UserListBean();
        for (UsersEntity ue : list) {
            uList.getList().add(new UserBean(ue.getUserName()));
        }
        try {
            stream.writeObject(uList);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
