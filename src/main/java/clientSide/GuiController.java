package clientSide;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import java.net.Socket;
import java.io.*;

/**
 * Created by Monteg on 11.03.2017.
 */
public class GuiController {
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;

    @FXML
    private TextField message;

    @FXML
    private Button send;

    @FXML
    private TextArea textArea;

    @FXML
    void buttonPressed(ActionEvent event) {
        readingMessages();
        Thread readerThread = new Thread(new WaitForReading());
        readerThread.start();
        new SendButton().actionPerformed(send);

    }

    public void readingMessages() {
        try {
            socket = new Socket("127.0.0.1", 7707);    //192.168.1.30
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("networking established");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class WaitForReading implements Runnable{

        public void run() {
            String sms;
            try {
                while ((sms = reader.readLine()) != null) {
                    System.out.println(sms);
                    textArea.appendText(sms + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class SendButton extends ActionEvent {
        public void actionPerformed(Button send) {
            try {
                writer.println(message.getText());
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            message.setText("");
            message.requestFocus();
        }
    }
}
