package bohdan.webchat.processors;

import bohdan.webchat.ConnectingStatus;
import bohdan.webchat.entity.UsersEntity;
import bohdan.webchat.registrationn.RegistrationRequest;
import bohdan.webchat.registrationn.RegistrationResponse;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static bohdan.webchat.DAO.UserDAO.addNewUser;
import static bohdan.webchat.DAO.UserDAO.userRegistrationCheck;

public class RegistrationRequestProcessor implements Processor {

    @Override
    public boolean canProcess(Object a) {
        return a instanceof RegistrationRequest;
    }

    @Override
    public void process(Object obj, ObjectOutputStream stream) {
        RegistrationResponse registrationResp = new RegistrationResponse();
        RegistrationRequest registrationReq = (RegistrationRequest) obj;
        System.out.println(registrationReq.toString());
        boolean verificationOf = userRegistrationCheck(registrationReq.getUsername());
        System.out.println(verificationOf);
        if (verificationOf == false) {
            UsersEntity usersEntity = new UsersEntity();
            usersEntity.setUserName(registrationReq.getUsername());
            usersEntity.setPassword(registrationReq.getPassword());
            usersEntity.setEmail(registrationReq.getEmail());
            addNewUser(usersEntity);
            registrationResp.setStatus(ConnectingStatus.OK);
        } else {
            registrationResp.setStatus(ConnectingStatus.EXIST);
        }
        try {
            stream.writeObject(registrationResp);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
