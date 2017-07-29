package bohdan.webchat.processors;

import bohdan.webchat.message.MessageBean;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import static bohdan.webchat.DAO.MessagesDAO.addMessages;
import static bohdan.webchat.sever.ChatServer.writers;

public class MessageProcassor implements Processor {

    @Override
    public boolean canProcess(Object a) {
        return a instanceof MessageBean;
    }

    @Override
    public void process(Object a, ObjectOutputStream stream) {
        MessageBean msg = (MessageBean) a;
        Iterator<ObjectOutputStream> iter = writers.iterator();
        while (iter.hasNext()) {
            try {
                ObjectOutputStream out = iter.next();
                out.writeObject(msg);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        addMessages(msg);
    }
}
