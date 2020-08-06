package uk.co.engine.interfiction.module.account.service;

import uk.co.engine.interfiction.module.account.exception.UserAccountCreationException;
import uk.co.engine.interfiction.shared.domain.EmailAddressVO;

public interface UserService {

    void createAccount(EmailAddressVO emailAddress, String passwordPlain, String name, String country)
            throws UserAccountCreationException;

    boolean authenticate(EmailAddressVO emailAddressVO, String passwordPlain);

}
