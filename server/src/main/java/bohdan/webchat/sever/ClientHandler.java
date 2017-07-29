package bohdan.webchat.sever;

import static bohdan.webchat.sever.ChatServer.writers;
import bohdan.webchat.processors.ProcessorsHandler;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by Monteg on 24.03.2017.
 */

public class ClientHandler extends Thread {
    private Socket socket;
    private ObjectOutputStream objectOutputS;
    private ObjectInputStream objectInputS;

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
                Object obj = objectInputS.readObject();

                ProcessorsHandler processorsHandler = new ProcessorsHandler();
                processorsHandler.findWhoWillProcess(obj, objectOutputS);

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}