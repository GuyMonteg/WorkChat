package bohdan.webchat;

import bohdan.webchat.controllers.LaunchClass;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Monteg on 10.03.2017.
 */
public class Main {
    public static ObjectOutputStream writeStream;
    public static ObjectInputStream readStream;

    public static void main(String[] args) throws Exception {
        try {
            Socket socket = new Socket(Data.host, Data.port);
            writeStream = new ObjectOutputStream(socket.getOutputStream());
            readStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Error when new socket created");
        }

        LaunchClass.launchUI();
    }
}
