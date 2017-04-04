package bohdan.webchat.controllers;

import bohdan.webchat.ConnectingStatus;
import bohdan.webchat.Main;
import bohdan.webchat.registrationnBeans.RegistrationRequest;
import bohdan.webchat.registrationnBeans.RegistrationResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Monteg on 13.03.2017.
 */
public class RegisterControllerW {

    @FXML private Button closeButton;
    @FXML private Button registerButton;
    @FXML private TextField name;
    @FXML private PasswordField password;
    @FXML private PasswordField passwordrepeat;
    @FXML private TextField email;
    @FXML private Label status;

    @FXML
    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void registerClicked(ActionEvent event) {
        if (!name.getText().isEmpty()) {
            if (!email.getText().isEmpty()) {
                if (!password.getText().isEmpty()) {
                    if (!passwordrepeat.getText().isEmpty()) {
                        if (password.getText().equals(passwordrepeat.getText())) {
                            RegistrationRequest request = new RegistrationRequest();
                            request.setUsername(name.getText());
                            request.setPassword(password.getText());
                            request.setEmail(email.getText());

                            ObjectOutputStream outputStream = Main.writeStream;
                            try {
                                outputStream.writeObject(request);
                                outputStream.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                                System.out.println("Exception when try to write object from registration window");
                            }
                            RegistrationResponse rr = receiveObject();

                            if (rr.getStatus().equals(ConnectingStatus.OK)) {
                                Stage stage = (Stage) registerButton.getScene().getWindow();
                                stage.close();
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Registration status");
                                alert.setHeaderText(null);
                                alert.setContentText("Registration was successful, \nwe will send you email with logins");
                                alert.showAndWait();
                            }
                            else if (rr.getStatus().equals(ConnectingStatus.EXIST)) {
                                status.setText("This username already exist!");
                            }

                        } else {
                            status.setText("Your passwords do not match!");
                        }
                    } else {
                        status.setText("Please repeat your password!");
                    }
                } else {
                    status.setText("Please enter your password!");
                }
            } else {
                status.setText("Please enter your email address!");
            }
        } else {
            status.setText("Please enter your name!");
        }
    }

    public RegistrationResponse receiveObject() {
        RegistrationResponse regResponse = null;
        try {
            ObjectInputStream readStream = Main.readStream;
            regResponse = (RegistrationResponse) readStream.readObject();
            System.out.println(regResponse.getStatus());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return regResponse;
    }
}
