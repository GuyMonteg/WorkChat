package bohdan.webchat.controllers;

import bohdan.webchat.ConnectingStatus;
import bohdan.webchat.Data;
import bohdan.webchat.Main;
import bohdan.webchat.messageBeans.MessageBean;
import bohdan.webchat.messageBeans.MessageListBean;
import bohdan.webchat.userBeans.RenameResponce;
import bohdan.webchat.userBeans.UserBean;
import bohdan.webchat.userBeans.UserListBean;
import bohdan.webchat.userBeans.UserNotificationBean;
import com.jfoenix.controls.JFXDrawer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Monteg on 11.03.2017.
 */

public class UserWController implements Initializable{
    private String name;
    private ObservableList<UserBean> obListOfUsers;

    @FXML private JFXDrawer drawer;
    @FXML private ImageView settings;
    @FXML private TextArea message;
    @FXML private Button send;
    @FXML private TextArea textArea;
    @FXML private Label userStatus;
    @FXML private ImageView logOut;
    @FXML private ListView<UserBean> listView;

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
                MessageBean messageBean = new MessageBean();
                messageBean.setId(Data.getiD());
                messageBean.setAuthor(Data.getName());
                messageBean.setMessageText(message.getText());
                messageBean.setDate(LocalDateTime.now().
                        format(DateTimeFormatter.
                                ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)));
                ObjectOutputStream outputStream = Main.writeStream;
                outputStream.writeObject(messageBean);
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
                    if (obj instanceof UserNotificationBean) {
                        UserNotificationBean unb = (UserNotificationBean) obj;
                        Platform.runLater(() -> {
                            textArea.appendText(unb.getConnectionStatus() + "\n");
                        });
                    }
                    if (obj instanceof MessageListBean) {
                        MessageListBean mlb = (MessageListBean) obj;
                        Platform.runLater(() -> {
                            ArrayList<MessageBean> listM = mlb.getList();
                            for (MessageBean m : listM) {
                                textArea.appendText(m.toString() + "\n");
                            }
                        });
                    }
                    if (obj instanceof UserListBean) {
                        UserListBean ub = (UserListBean) obj;
                        Platform.runLater(() -> {
                            obListOfUsers = FXCollections.observableArrayList(ub.getList());
                            listView.setItems(obListOfUsers);
                            listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                        });
                        //listView.setCellFactory(new PropertyValueFactory<String,>());
                    }
                    if (obj instanceof RenameResponce) {
                        RenameResponce rr = (RenameResponce) obj;
                        Platform.runLater(() -> {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Operation result");
                            alert.setHeaderText(null);
                            if (rr.getStatus() == ConnectingStatus.OK) {
                                userStatus.setText(rr.getNewName());
                                Data.setName(rr.getNewName());
                                alert.setContentText("Changes was successful!");
                            } else {
                                alert.setContentText("Mission failed!");
                            }
                            DialogPane dialogPane = alert.getDialogPane();
                            dialogPane.getStylesheets().add("styles/connWindowStyle.css");
                            alert.showAndWait();
                        });
                    }
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
    public void logOutMainW() {
        UserNotificationBean unb = new UserNotificationBean();
        unb.setConnectionStatus(Data.getName() + " is disconnected.");
        ObjectOutputStream outputStream = Main.writeStream;
        try {
            outputStream.writeObject(unb);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Platform.exit();
            System.exit(0);
        }
    }

    @FXML
    public void settingsM() {
        try {
            VBox box = FXMLLoader.load(getClass().getResource("/fxml/setBar.fxml"));
            drawer.setSidePane(box);
            drawer.setDisable(true);

            if (drawer.isShown()) {
                drawer.close();
                drawer.setDisable(true);
            } else {
                drawer.open();
                drawer.setDisable(false);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        drawer.setDisable(true);
    }
}