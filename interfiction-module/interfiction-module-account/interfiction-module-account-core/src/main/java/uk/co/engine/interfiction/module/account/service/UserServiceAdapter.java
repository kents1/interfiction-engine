package uk.co.engine.interfiction.module.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.engine.interfiction.module.account.domain.User;
import uk.co.engine.interfiction.module.account.exception.UserAccountCreationException;
import uk.co.engine.interfiction.module.account.port.HasAccountModifier;
import uk.co.engine.interfiction.module.account.port.HasAccountSelector;
import uk.co.engine.interfiction.shared.domain.EmailAddressVO;

@Service
public class UserServiceAdapter implements UserService {

    private final HasAccountModifier accountModifier;
    private final HasAccountSelector accountSelector;

    @Autowired
    public UserServiceAdapter(final HasAccountModifier accountModifier,
                              final HasAccountSelector accountSelector) {
        this.accountModifier = accountModifier;
        this.accountSelector = accountSelector;
    }

    @Override
    public void createAccount(final EmailAddressVO emailAddress, final String passwordPlain, final String name, final String country)
            throws UserAccountCreationException {
        final User user = accountSelector.findUsingId(emailAddress).orElse(new User(emailAddress));
        user.createAccountUsing(accountModifier, passwordPlain);
    }

    @Override
    public boolean authenticate(final EmailAddressVO emailAddressVO, final String passwordPlain) {
        return false;
    }

}
