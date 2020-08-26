package uk.co.engine.interfiction.module.account.repository.adapter;

import org.jdbi.v3.core.Jdbi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import uk.co.engine.interfiction.module.account.domain.User;
import uk.co.engine.interfiction.module.account.service.UserAccountCreationException;
import uk.co.engine.interfiction.module.account.model.Credential;
import uk.co.engine.interfiction.module.account.port.HasAccountModifier;
import uk.co.engine.interfiction.module.account.port.HasAccountSelector;
import uk.co.engine.interfiction.module.account.repository.command.InsertCredential;
import uk.co.engine.interfiction.shared.domain.EmailAddressVO;
import uk.co.engine.interfiction.module.account.repository.command.SelectCredentialByEmailAddress;

import java.util.Optional;

@Repository
public class JdbiUserRepository implements HasAccountModifier, HasAccountSelector {

    private final Jdbi jdbi;

    @Autowired
    public JdbiUserRepository(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Optional<User> findUsingId(final EmailAddressVO userId) {
        return jdbi.withHandle(new SelectCredentialByEmailAddress(userId.getEmailAddress()))
                .map(credential -> new User(new EmailAddressVO(credential.emailAddress())));
    }

    @Override
    @Transactional
    public void createAccountFor(final User user, final String password) throws UserAccountCreationException {
        if (findUsingId(user.getEmailAddress()).isPresent())
            throw new UserAccountCreationException();
        jdbi.useTransaction(new InsertCredential(new Credential(user.getEmailAddress().getEmailAddress(), password)));
    }

}
