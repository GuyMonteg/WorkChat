package bohdan.webchat.processors;

import bohdan.webchat.user.UserNotificationBean;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import static bohdan.webchat.sever.ChatServer.writers;

public class UserNotificationProcessor implements Processor {

    @Override
    public boolean canProcess(Object a) {
        return a instanceof UserNotificationBean;
    }

    @Override
    public void process(Object a, ObjectOutputStream stream) {
        UserNotificationBean unb = (UserNotificationBean) a;
        Iterator<ObjectOutputStream> iter = writers.iterator();
        while (iter.hasNext()) {
            try {
                ObjectOutputStream out = iter.next();
                out.writeObject(unb);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
