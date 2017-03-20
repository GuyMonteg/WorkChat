package clientSide;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * Created by Monteg on 13.03.2017.
 */
public class ConnectControllerW {

    @FXML private TextField hostName;
    @FXML private TextField portNum;
    @FXML private Button saveProperties;

    @FXML
    public void saveNewProperties() {
        if ((!hostName.getText().equals("")) && (!portNum.getText().equals(""))) {
            Data.setHost(hostName.getText());
            Data.setPort(Integer.parseInt(portNum.getText()));

            Stage stage = (Stage) saveProperties.getScene().getWindow();
            stage.close();
        }
    }
    public void lastPropertiesSet() {
        hostName.setText(Data.getHost());
        portNum.setText(String.valueOf(Data.getPort()));
    }

    @FXML
    public void enterPressed(KeyEvent key) {
        if (key.getCode().toString().equals("ENTER")) {
            saveNewProperties();
        }
    }
}
