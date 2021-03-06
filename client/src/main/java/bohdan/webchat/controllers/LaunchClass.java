package bohdan.webchat.controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Monteg on 23.03.2017.
 */
public class LaunchClass extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(
                "/fxml/loginWindow.fxml"));
        primaryStage.setTitle("Login");
        Scene scene = new Scene(root, 939.0, 530.0);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void launchUI(String... args) {
        launch(args);
    }
}
