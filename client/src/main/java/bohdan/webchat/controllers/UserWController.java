package bohdan.webchat.controllers;

import bohdan.webchat.Data;
/*import dbconnection.DBConnection;*/
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import java.net.Socket;
import java.io.*;

/**
 * Created by Monteg on 11.03.2017.
 */
public class UserWController {
    private String name;
    private String sentMessage;
    private String receivedMessage;
    private ObjectInputStream readStream;
    private ObjectOutputStream writeStream;
    private Socket socket;

    @FXML private TextField message;
    @FXML private Button send;
    @FXML private TextArea textArea;
    @FXML private Label userStatus;
    @FXML private ImageView logOut;
    @FXML private ListView<String> listView;

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

    public UserWController() {
        /*try {
            socket = new Socket(Data.host, Data.port);
            readStream = new ObjectInputStream(socket.getInputStream());
            writeStream = new ObjectOutputStream(socket.getOutputStream());
            this.name = Data.name;
            writeStream.writeUTF(name);
            receivedM();
        } catch (IOException e) {
            System.out.println("Error in UserWController");
        }*/
    }

    @FXML
    public void buttonSended() {
        /*if (!message.getText().isEmpty()) {
            try {
                sentMessage = message.getText();
                System.out.println(name + " : " + sentMessage);
                writeStream.writeUTF(sentMessage);
                message.setText("");
                message.requestFocus();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }
    public void receivedM() {
        /*Thread potokPolucheniyaM = new Thread() {
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
        potokPolucheniyaM.start();*/
    }

    public void enterPressed(KeyEvent key) {
        /*if (key.getCode().toString().equals("ENTER")) {
            buttonSended();
        }*/
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
