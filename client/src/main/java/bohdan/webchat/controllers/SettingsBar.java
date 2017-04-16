package bohdan.webchat.controllers;

import bohdan.webchat.ConnectingStatus;
import bohdan.webchat.Data;
import bohdan.webchat.Main;
import bohdan.webchat.userBeans.RenameResponce;
import bohdan.webchat.userBeans.UserBean;
import bohdan.webchat.userBeans.UserDelete;
import bohdan.webchat.userBeans.UserRename;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by Monteg on 14.04.2017.
 */

public class SettingsBar {
    private boolean notific = true;
    private boolean notSound = true;

    @FXML private Label gitHub;
    @FXML private Label emailSending;
    @FXML private Label deleteAndLeave;
    @FXML private Label info;
    @FXML private Button save;
    @FXML private Button cancel;
    @FXML private TextField name;
    @FXML private PasswordField password;
    @FXML private JFXToggleButton notification;
    @FXML private JFXToggleButton notifSound;

    @FXML
    public void changeAccount() {
        if (!name.getText().isEmpty()) {
            if (!password.getText().isEmpty()) {
                UserRename userRename = new UserRename();
                userRename.setOldName(Data.getName());
                userRename.setNewName(name.getText());
                userRename.setNewPass(password.getText());
                ObjectOutputStream out = Main.writeStream;
                try {
                    out.writeObject(userRename);
                    out.flush();
                    name.clear();
                    password.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Write new password");
            }
        } else {
            info.setText("Write new name");
        }
    }

    public void changeResponce() {
    /*Придет ли респонс в этот контроллер, скорее всего
        его получит UserWController так ка он непрерывно читает,
        как в таком случае вывести уведомление об успешном изменеии,
        может через тот же Alert?
        */
    }

    public void changeNotific() {
        if (notific == true) {

        }
    }

    @FXML
    public void linksToGitHub() {
        String urlString = "https://github.com/GuyMonteg/WorkChat";
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void emailSending() {

    }

    @FXML
    public void leaveThisPlace() {
        UserDelete user = new UserDelete();
        user.setUsername(Data.getName());
        ObjectOutputStream out = Main.writeStream;
        try {
            out.writeObject(user);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Platform.exit();
        System.exit(0);
    }

    @FXML
    public void cancelButton() {
        name.clear();
        password.clear();
    }

}
