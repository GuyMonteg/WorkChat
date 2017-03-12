package clientSide;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;

import java.net.Socket;
import java.io.*;
import java.net.SocketException;

/**
 * Created by Monteg on 11.03.2017.
 */
public class UserWController {
    private String name;
    private String sentMessage;
    private String receivedMessage;
    private DataInputStream readStream;
    private DataOutputStream writeStream;
    private Socket socket;

    @FXML
    private TextField message;

    @FXML
    private Button send;

    @FXML
    private TextArea textArea;

    public UserWController() {
        try {
            socket = new Socket(Data.host, Data.port);
            readStream = new DataInputStream(socket.getInputStream());
            writeStream = new DataOutputStream(socket.getOutputStream());
            this.name = Data.name;
            writeStream.writeUTF(name);
            receivedM();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    @FXML
    void buttonSended() {
        try {
            sentMessage = message.getText();
            System.out.println(name + " : " + sentMessage);
            writeStream.writeUTF(sentMessage);
            message.setText("");
            message.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receivedM() {
        Thread potokPolucheniyaM = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        receivedMessage = readStream.readUTF();
                        textArea.appendText(receivedMessage + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        potokPolucheniyaM.start();
    }

    public void enterPressed(KeyEvent key) {
        if (key.getCode().toString().equals("ENTER")) {
            buttonSended();
        }
    }

}
