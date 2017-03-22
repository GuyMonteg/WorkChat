package bohdan.webchat.web;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import bohdan.webchat.Data;

/**
 * Created by Monteg on 22.03.2017.
 */
public class SocketThread implements Runnable{
    public static ObjectInputStream readStream;
    public static ObjectOutputStream writeStream;
    public static Socket socket;

    @Override
    public void run() {
        try {
            socket = new Socket(Data.host, Data.port);
            readStream = new ObjectInputStream(socket.getInputStream());
            writeStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error when new socket created");
        }
    }
}
