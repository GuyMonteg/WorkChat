package clientSide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

/**
 * Created by Monteg on 10.03.2017.
 */
public class ClientMain extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/userWindow.fxml"));
        primaryStage.setTitle("WorkChat");
        Scene scene = new Scene(root, 1280.0, 768.0);
        scene.getStylesheets().add(0, "file:///D://Hrygorovoch//WorkChatProject//src//main//resources//styles//loginWindowStyle.css");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
