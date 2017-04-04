package bohdan.webchat.controllers;

import bohdan.webchat.Data;
import bohdan.webchat.Main;
import bohdan.webchat.messageBeans.MessageRequest;
import bohdan.webchat.messageBeans.MessageResponse;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import javafx.collections.ObservableList;

/**
 * Created by Monteg on 11.03.2017.
 */
public class UserWController {
    private String name;
    private String sentMessage;
    //private String receivedMessage;
    private Socket socket;

    @FXML private TextField message;
    @FXML private Button send;
    @FXML private TextArea textArea;
    @FXML private Label userStatus;
    @FXML private ImageView logOut;
    @FXML private ListView<String> listView;

    public UserWController() {
        receivedM();
    }

    @FXML
    public void listDemostrate() {
        //ObservableList<String> users = FXCollections.observableArrayList(DBConnection.getUsersList());
        //listView.setItems(users);
        //listView.refresh();
    }

    @FXML
    public void setUserStatus(String name) {
        userStatus.setText(name);
    }

    @FXML
    public void buttonSended() {
        if (!message.getText().isEmpty()) {
            try {
                MessageRequest messageRequest = new MessageRequest();
                messageRequest.setAuthor(Data.getName());
                messageRequest.setMessageText(message.getText());
                messageRequest.setDate(Date.valueOf(LocalDate.now()));
                ObjectOutputStream outputStream = Main.writeStream;
                outputStream.writeObject(messageRequest);
                outputStream.flush();

                message.setText("");
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
                    MessageRequest mr = null;
                    try {
                        ObjectInputStream readStream = Main.readStream;
                        mr = (MessageRequest) readStream.readObject();

                        textArea.appendText(mr.toString() + "\n");
                        System.out.println("in receiveM " + mr.toString());
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        msgrsgThread.start();
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

        /*Stage stage2 = new Stage();
        FXMLLoader loading = new FXMLLoader(getClass().getResource("../fxml/loginWindow.fxml"));
        Parent connW = loading.load();
        Scene scena = new Scene(connW, 450.0, 315.0);
        scena.getStylesheets().add(0,
                "file:///D://Hrygorovoch//WorkChatProject//src//main//resources//styles//loginWindowStyle.css");
        stage2.setScene(scena);
        stage2.setResizable(false);
        stage2.setTitle("Registration");
        stage2.show();*/
    }

}
