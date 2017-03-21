package clientSource.controlls;

import clientSource.Data;
import dbconnection.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

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


    public void whenClickedLogin() throws IOException {
        Data.name = name.getText();
        System.out.println(Data.host + " " + Data.port + " " + Data.name);

         if (DBConnection.loginUser(name.getText(), password.getText()) == true) {
             Stage stage;
             stage = (Stage) (name.getScene().getWindow());
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/userWindow.fxml"));
             Parent userW = loader.load();

             UserWController usernameC = loader.getController();     //take user name and put it in main window
             usernameC.setUserStatus(name.getText());
             usernameC.listDemostrate();

             Scene scena = new Scene(userW, 1280.0, 768.0);
             scena.getStylesheets().add(0,
                     "file:///D://Hrygorovoch//WorkChatProject//src//main//resources//styles//userWindowStyle.css");
             stage.setScene(scena);
             stage.setTitle(Data.name);
             stage.show();
         } else {
             connStatus.setText("Wrong username or password!");
         }
        if (password.getText().equals("")) {
            connStatus.setText("Please write your password!");
        }
        if (name.getText().equals("")) {
             connStatus.setText("Please write your name!");
         }
    }

    public void whenSettingsClicked() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loading = new FXMLLoader(getClass().getResource("../fxml/connectWindow.fxml"));
        Parent connW = loading.load();

        ConnectControllerW ccW = loading.getController();
        ccW.lastPropertiesSet();

        Scene scena = new Scene(connW, 310.0, 231.0);
        scena.getStylesheets().add(0,
                "file:///D://Hrygorovoch//WorkChatProject//src//main//resources//styles//connWindowStyle.css");
        stage.setScene(scena);
        stage.setResizable(false);
        stage.setTitle("Settings");
        stage.show();
    }

    public void registerWindowClicked() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loading = new FXMLLoader(getClass().getResource("../fxml/registerWindow.fxml"));
        Parent connW = loading.load();
        Scene scena = new Scene(connW, 509.0, 278.0);
        scena.getStylesheets().add(0,
                "file:///D://Hrygorovoch//WorkChatProject//src//main//resources//styles//registerWindowStyle.css");
        stage.setScene(scena);
        stage.setResizable(false);
        stage.setTitle("Registration");
        stage.show();
    }

    @FXML
    public void enterPressed(KeyEvent key) {
        if (key.getCode().toString().equals("ENTER")) {
            try {
                whenClickedLogin();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error when Enter was pressed!");
            }
        }
    }
}
