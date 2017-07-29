package bohdan.webchat.processors;

import java.io.ObjectOutputStream;

/**
 * Created by Monteg on 16.07.2017.
 */
interface Processor {
    boolean canProcess(Object a);
    void process(Object a, ObjectOutputStream stream);
}
