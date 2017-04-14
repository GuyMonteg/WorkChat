package bohdan.webchat.controllers;

import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * Created by Monteg on 14.04.2017.
 */
public class SettingsBar {

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



}
