package bohdan.webchat.sever;

import bohdan.webchat.config.DBProperties;
import org.flywaydb.core.Flyway;
import java.net.*;
import java.io.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Monteg on 11.03.2017.
 */

public class ChatServer {
    public static Set<ClientHandler> setOfConnections;
    public static Set<ObjectOutputStream> writers;

    public ChatServer() {
        setOfConnections = Collections.synchronizedSet(new HashSet<ClientHandler>(100));
        writers = Collections.synchronizedSet(new HashSet<ObjectOutputStream>(100));
    }

    public void go() {
        ServerSocket serverSock = null;
        Socket clientSocket = null;
        try {
            serverSock = new ServerSocket(7707);
            System.out.println("Server started!");
            while (true) {
                clientSocket = serverSock.accept();
                System.out.println("new client connected");
                System.out.println(clientSocket.getInetAddress() + " : " + clientSocket.getPort());

                ClientHandler receiver = new ClientHandler(clientSocket);
                //setOfConnections.add(clientSocket);
                setOfConnections.add(receiver);
                receiver.start();
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

    public static void main(String[] args) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(DBProperties.URL, DBProperties.USER, DBProperties.PASW);
        flyway.migrate();
        new ChatServer().go();
    }

}
