package bohdan.webchat.severControls;

import bohdan.webchat.*;
import bohdan.webchat.entity.MessagesEntity;
import bohdan.webchat.entity.UsersEntity;
import bohdan.webchat.loginBeans.LoginRequest;
import bohdan.webchat.loginBeans.LoginResponse;
import bohdan.webchat.messageBeans.MessageBean;
import bohdan.webchat.messageBeans.MessageListBean;
import bohdan.webchat.registrationnBeans.RegistrationRequest;
import bohdan.webchat.registrationnBeans.RegistrationResponse;
import bohdan.webchat.userBeans.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import static bohdan.webchat.DAO.MessagesDAO.addMessages;
import static bohdan.webchat.DAO.MessagesDAO.deleteMessagesByUser;
import static bohdan.webchat.DAO.MessagesDAO.getMessagesByDate;
import static bohdan.webchat.DAO.UserDAO.*;
import static bohdan.webchat.severControls.ChatServer.writers;

/**
 * Created by Monteg on 24.03.2017.
 */

public class ClientHandler extends Thread {
    private Socket socket;
    private ObjectOutputStream objectOutputS;
    private ObjectInputStream objectInputS;
    private Object obj = null;

    public ClientHandler(Socket socket) {
        this.socket = socket;
        try {
            objectOutputS = new ObjectOutputStream(socket.getOutputStream());
            objectInputS = new ObjectInputStream(socket.getInputStream());
            writers.add(objectOutputS);
        } catch (IOException e) {
            System.out.println("Stream failed");
        }
    }

    @Override
    public void run() {
        System.out.println("new socked connected");
        try {
            while (true) {
                obj = objectInputS.readObject();
                if (obj instanceof LoginRequest) {
                    LoginResponse response = userIdentification();
                    objectOutputS.writeObject(response);
                    objectOutputS.flush();
                    System.out.println(response.toString() + "в ране");
                    if (response.getStatus().equals(ConnectingStatus.OK)) {
                        viewMessagesList();                                 //try to get list of old messages
                        viewUsersList();
                    }
                }
                if (obj instanceof RegistrationRequest) {
                    RegistrationResponse registresponse = userRegistration();
                    System.out.println(registresponse.toString());
                    objectOutputS.writeObject(registresponse);
                    objectOutputS.flush();
                }
                if (obj instanceof MessageBean) {
                    MessageBean msg = (MessageBean) obj;
                    tellEveryone(msg);
                    addMessages(msg);
                    System.out.println(msg.toString());
                }
                if (obj instanceof UserNotificationBean) {
                    UserNotificationBean unb = (UserNotificationBean) obj;
                    notifConnectOrDisconnect(unb);
                }
                if (obj instanceof UserRename) {
                    RenameResponce rename = renameUser();
                    objectOutputS.writeObject(rename);
                    objectOutputS.flush();
                }
                if (obj instanceof UserDelete) {
                    deleteUser();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser() {
        UserDelete delete = (UserDelete) obj;
        UsersEntity entity = new UsersEntity();
        entity.setUserName(delete.getUsername());
        boolean result = deleteMessagesByUser(entity);
        if (result == true){
        deleteUserFromDB(entity);
        }
    }

    public RenameResponce renameUser() {
        RenameResponce renameResponce = new RenameResponce();
        UserRename rename = (UserRename) obj;
        boolean verify = userRename(rename);
        if (verify == true) {
            renameResponce.setNewName(rename.getNewName());
            renameResponce.setStatus(ConnectingStatus.OK);
        } else {
            renameResponce.setStatus(ConnectingStatus.FAIL);
        }
        return renameResponce;
    }

    public RegistrationResponse userRegistration() {
        RegistrationResponse registrationResp = new RegistrationResponse();
        RegistrationRequest registrationReq = (RegistrationRequest) obj;
        System.out.println(registrationReq.toString());
        boolean verificationOf = userRegisterControl(registrationReq.getUsername());
        System.out.println(verificationOf);
        if (verificationOf == false) {
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setUserName(registrationReq.getUsername());
            usersEntity.setPassword(registrationReq.getPassword());
            usersEntity.setEmail(registrationReq.getEmail());
            addNewUser(usersEntity);
            registrationResp.setStatus(ConnectingStatus.OK);
        } else {
            registrationResp.setStatus(ConnectingStatus.EXIST);
        }
        return registrationResp;
    }

    public LoginResponse userIdentification() {
        LoginResponse loginResponse = new LoginResponse();
        LoginRequest loginRequest = (LoginRequest) obj;
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
        return loginResponse;
    }

    public void tellEveryone(MessageBean msg) {
        Iterator<ObjectOutputStream> iter = writers.iterator();
        while (iter.hasNext()) {
            try {
                ObjectOutputStream out = iter.next();
                out.writeObject(msg);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void notifConnectOrDisconnect(UserNotificationBean unb) {
        Iterator<ObjectOutputStream> iter = writers.iterator();
        while (iter.hasNext()) {
            try {
                ObjectOutputStream out = iter.next();
                out.writeObject(unb);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void viewMessagesList() {
        ArrayList<MessagesEntity> list = getMessagesByDate();
        ArrayList<MessageBean> mlist = new ArrayList<>();
        MessageListBean listbean = new MessageListBean();
        for (MessagesEntity me : list) {
            mlist.add(new MessageBean(me.getAuthor(), me.getMessageText(), me.getDate()));
        }
        listbean.setList(mlist);
        try {
            objectOutputS.writeObject(listbean);
            objectOutputS.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void viewUsersList() {
        ArrayList<UsersEntity> list = getUsersList();
        ArrayList<UserBean> userBeans = new ArrayList<>();
        UserListBean uList = new UserListBean();
        for (UsersEntity ue : list) {
            userBeans.add(new UserBean(ue.getUserName()));
        }
        uList.setList(userBeans);
        try {
            objectOutputS.writeObject(uList);
            objectOutputS.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}