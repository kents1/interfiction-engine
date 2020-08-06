package uk.co.engine.interfiction.module.account.domain;

import uk.co.engine.interfiction.module.account.exception.UserAccountCreationException;
import uk.co.engine.interfiction.module.account.port.HasAccountModifier;
import uk.co.engine.interfiction.shared.domain.EmailAddressVO;

import java.util.Objects;

public class User {

    private final EmailAddressVO emailAddress;

    public User(final EmailAddressVO emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void createAccountUsing(final HasAccountModifier accountModifier, final String password)
            throws UserAccountCreationException {
        validate();
        accountModifier.createAccountFor(this, password);
    }

    public EmailAddressVO getEmailAddress() {
        return emailAddress;
    }

    private void validate() {
        if (Objects.isNull(emailAddress.getEmailAddress()))
            throw new IllegalArgumentException("Email address cannot be null");
    }

}
