package bohdan.webchat.controllers;

import bohdan.webchat.Data;
import bohdan.webchat.Main;
import bohdan.webchat.messageBeans.MessageBean;
import bohdan.webchat.userBeans.UserBean;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Monteg on 11.03.2017.
 */

public class UserWController {
    private String name;
    private ObservableList<String> obListOfUsers;
    private Socket socket;

    @FXML private TextArea message;
    @FXML private Button send;
    @FXML private TextArea textArea;
    @FXML private Label userStatus;
    @FXML private ImageView logOut;
    @FXML private ListView listView;

    public UserWController() {
        receivedM();
    }

    @FXML
    public void setUserStatus(String name) {
        userStatus.setText(name);
    }

    @FXML
    public void buttonSended() {
        if (!message.getText().isEmpty()) {
            try {
                MessageBean messageRequest = new MessageBean();
                messageRequest.setAuthor(Data.getName());
                messageRequest.setMessageText(message.getText());
                messageRequest.setDate(Date.valueOf(LocalDate.now()));
                ObjectOutputStream outputStream = Main.writeStream;
                outputStream.writeObject(messageRequest);
                outputStream.flush();
                message.clear();
                message.requestFocus();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Exception when try to write object from registration window");
            }
        }
    }

    public void receivedM() {
        Thread msgrsgThread = new Thread() {
            @Override
            public void run() {
            while (true) {
                Object obj = null;
                try {
                    ObjectInputStream readStream = Main.readStream;
                    obj = readStream.readObject();
                    if (obj instanceof MessageBean) {
                        MessageBean mb = (MessageBean) obj;
                        textArea.appendText(mb.toString() + "\n");
                        System.out.println("in receiveM " + mb.toString());
                    }
                    if (obj instanceof UserBean) {
                        UserBean ub = (UserBean) obj;
                        obListOfUsers = FXCollections.observableArrayList(ub.getList());
                        listView.setItems(obListOfUsers);
                        //listView.setCellFactory();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            }
        };
        msgrsgThread.start();
    }

    @FXML
    public void initialize() {
    }

    public void enterPressed(KeyEvent key) {
        if (key.getCode().toString().equals("ENTER")) {
            buttonSended();
        }
    }

    @FXML
    public void logOutMainW() throws IOException {
        Platform.exit();
        System.exit(0);
    }
}