package bohdan.webchat.severControls;

import bohdan.webchat.*;
import bohdan.webchat.entity.MessagesEntity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static bohdan.webchat.DAO.UserDAO.findUserByName;
import static bohdan.webchat.severControls.ChatServer.writers;

/**
 * Created by Monteg on 24.03.2017.
 */

public class ClientHandler extends Thread {
    private RegistrationResponse regResponse = new RegistrationResponse();

    private Socket socket;
    private ObjectOutputStream objectOutputS;
    private ObjectInputStream objectInputS;

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
            //while (objectInputS.readObject() != null) {
                if (objectInputS.readObject() instanceof LoginRequest) {
                    System.out.println(userIdentification().toString() + "в ране");
                    objectOutputS.writeObject(userIdentification());
                    objectOutputS.flush();
                }
            //}
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public LoginResponse userIdentification() {
        LoginResponse loginResponse = new LoginResponse();
        try {
            LoginRequest loginRequest = (LoginRequest) objectInputS.readObject();
            System.out.println(loginRequest.toString());
            if (findUserByName(loginRequest.getUsername()) != null) {
                if (findUserByName(loginRequest.getUsername()).getPassword()
                        .equals(loginRequest.getUserpassword())) {

                    loginResponse.setStatus(ConnectingStatus.OK);
                    System.out.println(loginResponse.getStatus());
                } else {
                    loginResponse.setStatus(ConnectingStatus.WRONGPASS);
                }
            } else {
                loginResponse.setStatus(ConnectingStatus.WRONGNAME);
            }
        } catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("Bug in userIdentification method!");
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
