package clientSide;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Monteg on 12.03.2017.
 */
public class LoginController {

    @FXML private TextField name;

    @FXML private PasswordField password;

    @FXML private Button loginIn;

    public void whenClickedLogin() throws IOException {
        Data.name = name.getText();
        System.out.println(Data.host + " " + Data.port + " " + Data.name);

        Stage stage;
        stage = (Stage) (name.getScene().getWindow());
        Parent userW = FXMLLoader.load(getClass().getResource("../fxml/userWindow.fxml"));
        Scene scena = new Scene(userW, 1280.0, 768.0);
        scena.getStylesheets().add(0,
                "file:///D://Hrygorovoch//WorkChatProject//src//main//resources//styles//userWindowStyle.css");
        stage.setScene(scena);
        stage.setTitle(Data.name);
        stage.show();
    }
}
