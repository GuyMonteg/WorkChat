package serverSide;

import java.util.ArrayList;
import java.util.Iterator;
import java.net.*;
import java.io.*;

/**
 * Created by Monteg on 11.03.2017.
 */
public class ChatServer {
    private ArrayList messageList;

    public class HandleClient implements Runnable {
        BufferedReader reader;
        Socket socket;

        public HandleClient(Socket clientSocket) {
            try {
                socket = clientSocket;
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            String mess;
            try {
                while ((mess = reader.readLine()) != null) {
                    System.out.println(mess);
                    tellEveryone(mess);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ChatServer().go();
    }

    public void go() {
        messageList = new ArrayList();
        try {
            ServerSocket serverSock = new ServerSocket(7707);
            while (true) {
                Socket clientSocket = serverSock.accept();
                PrintWriter printMessage = new PrintWriter(clientSocket.getOutputStream());
                messageList.add(printMessage);
                Thread inputThread = new Thread(new HandleClient(clientSocket));
                inputThread.start();
                System.out.println("Connected to server!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tellEveryone(String message) {
        Iterator it = messageList.iterator();
        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
