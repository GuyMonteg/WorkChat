package clientSource;

import dbconnection.DBConnection;
import dbconnection.UsersEntity;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Monteg on 13.03.2017.
 */
public class RegisterControllerW {

    @FXML private Button closeButton;
    @FXML private Button registerButton;
    @FXML private TextField name;
    @FXML private TextField password;
    @FXML private TextField passwordrepeat;
    @FXML private TextField email;
    @FXML private Label status;

    @FXML
    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void registerClicked(ActionEvent event) {
        if (passwordrepeat.getText().equals("")) {
            status.setText("Please repeat your password!");
        }
        if (password.getText().equals("")) {
            status.setText("Please enter your password!");
        }
        if (email.getText().equals("")) {
            status.setText("Please enter your email address!");
        }
        if (name.getText().equals("")) {
            status.setText("Please enter your name!");
        }

        if (DBConnection.userRegisterControl(name.getText()) == false) {
            if (password.getText().equals(passwordrepeat.getText())) {
                if ((!name.getText().equals("")) && (!password.getText().equals(""))
                        && (!passwordrepeat.getText().equals("")) && (!email.getText().equals(""))) {
                    DBConnection.addNewUser(new UsersEntity(name.getText(), password.getText(), email.getText()));
                    Stage stage = (Stage) registerButton.getScene().getWindow();
                    stage.close();
                }
            } else {
                status.setText("Your passwords do not match!");
            }
        } else {
            status.setText("This username already exist!");
        }
    }
}
