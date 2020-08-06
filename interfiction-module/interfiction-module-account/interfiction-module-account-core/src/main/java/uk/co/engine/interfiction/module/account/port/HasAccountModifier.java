package uk.co.engine.interfiction.module.account.port;

import uk.co.engine.interfiction.module.account.domain.User;
import uk.co.engine.interfiction.module.account.exception.UserAccountCreationException;

public interface HasAccountModifier {

    void createAccountFor(User user, String password)
            throws UserAccountCreationException;

}
