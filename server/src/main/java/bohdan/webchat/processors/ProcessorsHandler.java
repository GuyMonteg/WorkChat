package bohdan.webchat.processors;

import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Monteg on 16.07.2017.
 */

public class ProcessorsHandler {
    private List<? extends Processor> processorsList;

    public ProcessorsHandler() {
        processorsList = Arrays.asList(new LoginRequestProcessor(),
                new RegistrationRequestProcessor(), new MessageProcassor(),
                new UserNotificationProcessor(), new UserRenameProcessor(),
                new UserDeleteProcessor());
    }

    public void findWhoWillProcess(Object obj, ObjectOutputStream stream) {
        for (Processor o : processorsList) {
            if (o.canProcess(obj)) {
                o.process(obj, stream);
            }
        }
    }

}
