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
    public static ObjectOutputStream writeStream;
    public static ObjectInputStream readStream;
    public static Socket socket;

    @Override
    public void run() {
        try {
            socket = new Socket(Data.host, Data.port);
            writeStream = new ObjectOutputStream(socket.getOutputStream());
            readStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Error when new socket created");
        }
    }
}
