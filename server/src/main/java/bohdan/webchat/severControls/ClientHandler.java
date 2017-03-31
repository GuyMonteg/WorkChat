package bohdan.webchat.severControls;

import bohdan.webchat.*;
import bohdan.webchat.entity.MessagesEntity;
import bohdan.webchat.entity.UsersEntity;
import bohdan.webchat.loginBeans.LoginRequest;
import bohdan.webchat.loginBeans.LoginResponse;
import bohdan.webchat.registrationnBeans.RegistrationRequest;
import bohdan.webchat.registrationnBeans.RegistrationResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Iterator;

import static bohdan.webchat.DAO.UserDAO.addNewUser;
import static bohdan.webchat.DAO.UserDAO.findUserByName;
import static bohdan.webchat.DAO.UserDAO.userRegisterControl;
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
        } catch (IOException e) {
            System.out.println("Stream failed");
        }

    }

    public void run() {
        System.out.println("new socked connected");
        try {
            while (true) {
                obj = objectInputS.readObject();
                if (obj instanceof LoginRequest) {
                    LoginResponse response = userIdentification();
                    System.out.println(response.toString() + "в ране");

                    objectOutputS.writeObject(response);
                    objectOutputS.flush();
                }
                if (obj instanceof RegistrationRequest) {
                    RegistrationResponse registresponse = userRegistration();
                    System.out.println(registresponse.toString());

                    objectOutputS.writeObject(registresponse);
                    objectOutputS.flush();
                }
                //tellEveryone(MessagesRequest);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private RegistrationResponse userRegistration() {
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

        public void tellEveryone(MessagesEntity message) {
            try {
                Iterator<ObjectOutputStream> iter = writers.iterator();
                while (iter.hasNext()) {
                    objectOutputS.writeObject(message);
                    objectOutputS.flush();
                }
            } catch (Exception ex) {
                System.out.println("Getting the client name and message failed!" + ex.getMessage());
            }
        }


    }
