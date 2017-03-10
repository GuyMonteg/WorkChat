package clientSide;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Monteg on 10.03.2017.
 */
public class Main  extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../fxml/loginWindow.fxml"));
        primaryStage.setTitle("Login");
        Scene scene = new Scene(root, 450.0, 315.0);
        scene.getStylesheets().add(0, "file:///D://Hrygorovoch//WorkChatProject//src//main//resources//styles//loginWindowStyle.css");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
