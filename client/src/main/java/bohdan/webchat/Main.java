package bohdan.webchat;

import bohdan.webchat.web.SocketThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Monteg on 10.03.2017.
 */
public class Main extends Application{



    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(
                "/fxml/loginWindow.fxml"));
        primaryStage.setTitle("Login");
        Scene scene = new Scene(root, 450.0, 315.0);
        scene.getStylesheets().add(0,
                "file:///D://Hrygorovoch//WorkChatProject//client//src//main//resources//styles//loginWindowStyle.css");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {

        Socket socket = new Socket(Data.host, Data.port);
        ObjectOutputStream writeStream = new ObjectOutputStream(socket.getOutputStream());

        UserBean bean = new UserBean();
        bean.setName("sdfgfd");
        bean.setPassword("sds");

        writeStream.writeObject(bean);
        writeStream.flush();

        //new Thread(new SocketThread()).start();

        //launch(args);
    }
}
