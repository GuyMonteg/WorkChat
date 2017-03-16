package serverSide;

import config.DBProperties;
import org.flywaydb.core.Flyway;
import java.util.*;
import java.net.*;
import java.io.*;

/**
 * Created by Monteg on 11.03.2017.
 */

public class ChatServer {
    private Map<String, DataOutputStream> messageList;

    public ChatServer() {
        messageList = Collections.synchronizedMap(new HashMap<String, DataOutputStream>());
    }

    public void go() {
        ServerSocket serverSock = null;
        Socket clientSocket = null;
        try {
            serverSock = new ServerSocket(7707);
            System.out.println("Server started!");
            while (true) {
                clientSocket = serverSock.accept();
                //System.out.println(clientSocket.getInetAddress() + " : " + clientSocket.getPort());
                ServerReceiver receiver = new ServerReceiver(clientSocket);
                ServerReceiver potok = receiver;
                potok.start();
            }
        } catch (IOException e) {
            System.out.println("Connection failed!");
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
                DataOutputStream out = messageList.get(name);
                out.writeUTF(message);
            } catch (Exception ex) {
                System.out.println("Getting the client name and message failed!");
            }
        }
    }

    public class ServerReceiver extends Thread {
        DataInputStream dinputS;
        DataOutputStream doutputS;
        Socket socket;

        public ServerReceiver(Socket socket) {
            this.socket = socket;
            try {
                dinputS = new DataInputStream(socket.getInputStream());
                doutputS = new DataOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                System.out.println("Stream failed");
            }
        }

        public void run() {
            String name = "";
            try {
                name = dinputS.readUTF();
                tellEveryone(name + " connected to chat.");
                messageList.put(name, doutputS);
                System.out.println("Current user : " + messageList.size());
                while (dinputS != null) {
                    tellEveryone(name + " : " + dinputS.readUTF());
                }
            } catch (IOException e) {
                System.out.println("Reading is failed!");
            } finally {
                tellEveryone(name + " disconnected.");
                messageList.remove(name);

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
