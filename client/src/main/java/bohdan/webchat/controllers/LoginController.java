package bohdan.webchat.controllers;

import bohdan.webchat.*;
import bohdan.webchat.login.LoginRequest;
import bohdan.webchat.login.LoginResponse;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.*;

/**
 * Created by Monteg on 12.03.2017.
 */
public class LoginController {

    @FXML private TextField name;
    @FXML private PasswordField password;
    @FXML private Button loginIn;
    @FXML private Button register;
    @FXML private Label settings;
    @FXML private Label connStatus;


    public void whenClickedLogin() {
                if (!name.getText().isEmpty()) {
                    if (!password.getText().isEmpty()) {
                        LoginRequest loginRequest = new LoginRequest();
                        loginRequest.setUsername(name.getText());
                        loginRequest.setUserpassword(password.getText());

                        ObjectOutputStream outputStream = Main.writeStream;
                        try {
                            outputStream.writeObject(loginRequest);
                            outputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("Exception when try to write object from login");
                        }
                        LoginResponse lr = receiveObject();

                        if (lr.getStatus().equals(ConnectingStatus.OK)) {
                            Data.setiD(lr.getId());
                            mainWload();
                        }
                        if (lr.getStatus().equals(ConnectingStatus.WRONGNAME)) {
                            connStatus.setText("Wrong user name !");
                        }
                        if (lr.getStatus().equals(ConnectingStatus.WRONGPASS)) {
                            connStatus.setText("Wrong password !");
                        }
                    } else {
                        connStatus.setText("Please write your password!");
                    }
                } else {
                    connStatus.setText("Please write your name!");
                }
    }

    public LoginResponse receiveObject() {
        LoginResponse loginResponse = null;
        try {
            ObjectInputStream readStream = Main.readStream;
            loginResponse = (LoginResponse) readStream.readObject();
            System.out.println(loginResponse.getStatus());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loginResponse;
    }

    public void whenSettingsClicked() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loading = new FXMLLoader(getClass().getResource("/fxml/connectWindow.fxml"));
        Parent connW = loading.load();

        ConnectControllerW ccW = loading.getController();
        //ccW.lastPropertiesSet();

        Scene scena = new Scene(connW, 393.0, 270.0);
        stage.setScene(scena);
        stage.setResizable(false);
        stage.setTitle("Settings");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void registerWindowClicked() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loading = new FXMLLoader(getClass().getResource("/fxml/registerWindow.fxml"));
        Parent connW = loading.load();
        Scene scena = new Scene(connW, 585.0, 335.0);
        stage.setScene(scena);
        stage.setResizable(false);
        stage.setTitle("Registration");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void mainWload() {
        Data.setName(name.getText());
        Stage stage;
        stage = (Stage) (name.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userWindow.fxml"));
        Parent userW = null;
        try {
            userW = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception when try to load fxml loader");
        }

        UserWController usernameC = loader.getController();     //take user name and put it in main window
        usernameC.setUserStatus(name.getText());

        Scene scena = new Scene(userW, 1280.0, 768.0);
        stage.setScene(scena);
        stage.setTitle(Data.getName());
        stage.show();
    }

    @FXML
    public void enterPressed(KeyEvent key) {
        if (key.getCode().toString().equals("ENTER")) {
            whenClickedLogin();
        }
    }
}
