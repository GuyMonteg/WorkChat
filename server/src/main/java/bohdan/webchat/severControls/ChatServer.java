package bohdan.webchat.severControls;

import bohdan.webchat.LoginRessponce;
import bohdan.webchat.UserBean;
import bohdan.webchat.config.DBProperties;
import org.flywaydb.core.Flyway;

import java.util.*;
import java.net.*;
import java.io.*;

/**
 * Created by Monteg on 11.03.2017.
 */

public class ChatServer {
    private Map<String, ObjectOutputStream> messageList;

    public ChatServer() {
        messageList = Collections.synchronizedMap(new HashMap<String, ObjectOutputStream>());
    }

    public void go() {
        ServerSocket serverSock = null;
        Socket clientSocket = null;
        try {
            serverSock = new ServerSocket(7707);
            System.out.println("Server started!");
           // while (true) {
                clientSocket = serverSock.accept();
            ObjectInputStream dinputS = new ObjectInputStream(clientSocket.getInputStream());
            UserBean bean = (UserBean) dinputS.readObject();
            System.out.println(bean);
            //LoginRessponce lr = new LoginRessponce();
            //lr.setStatus(bean.toString());


            //System.out.println(clientSocket.getInetAddress() + " : " + clientSocket.getPort());
                /*ServerReceiver receiver = new ServerReceiver(clientSocket);
                receiver.start();*/
           // }
        } catch (IOException e) {
            System.out.println("Connection failed!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (serverSock != null) {
                try {
                    serverSock.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void tellEveryone(String message) {
        Iterator<String> it = messageList.keySet().iterator();
        while (it.hasNext()) {
            try {
                String name = it.next();
                ObjectOutputStream out = messageList.get(name);
                out.writeUTF(message);
            } catch (Exception ex) {
                System.out.println("Getting the client name and message failed!");
            }
        }
    }

    public class ServerReceiver extends Thread {
        ObjectOutputStream doutputS;
        ObjectInputStream dinputS;
        Socket socket;

        public ServerReceiver(Socket socket) {
            this.socket = socket;
            try {
                dinputS = new ObjectInputStream(socket.getInputStream());
                doutputS = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                System.out.println("Stream failed");
            }
        }

        public void run() {
            System.out.println("new socked connected");
            UserBean userBean = null;
            try {
                userBean =(UserBean) dinputS.readObject();
                System.out.println(userBean.toString());


                /*tellEveryone(name + " connected to chat.");
                messageList.put(name, doutputS);
                System.out.println("Current user : " + messageList.size());
                while (dinputS != null) {
                    tellEveryone(name + " : " + dinputS.readUTF());
                }*/
            } catch (IOException e) {
                System.out.println("Reading is failed!");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            /*} finally {
                tellEveryone(name + " disconnected.");
                messageList.remove(name);
*/
            }
        }
    }

    public static void main(String[] args) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(DBProperties.URL, DBProperties.USER, DBProperties.PASW);
        flyway.migrate();
        new ChatServer().go();

    }

}
