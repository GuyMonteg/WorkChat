package bohdan.webchat;

import bohdan.webchat.controllers.LaunchClass;
import bohdan.webchat.controllers.LoginController;
import bohdan.webchat.web.SocketThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Monteg on 10.03.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        SocketThread socketThread = new SocketThread();
        Thread thread = new Thread(socketThread);
        thread.start();

        LaunchClass.launchUI();
    }
}
