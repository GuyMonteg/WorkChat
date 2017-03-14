package clientSide;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Created by Monteg on 13.03.2017.
 */
public class RegisterControllerW {

    @FXML private Button closeButton;

    @FXML
    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
