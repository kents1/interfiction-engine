package uk.co.engine.interfiction.module.account.repository.command;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.HandleCallback;
import org.jdbi.v3.core.JdbiException;
import uk.co.engine.interfiction.module.account.model.Credential;
import uk.co.engine.interfiction.module.account.repository.dao.CredentialDAO;

import java.util.Objects;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class SelectCredentialByEmailAddress implements HandleCallback<Optional<Credential>, JdbiException> {

    private final String emailAddress;

    public SelectCredentialByEmailAddress(final String emailAddress) {
        requireNonNull(emailAddress);
        this.emailAddress = emailAddress;
    }

    @Override
    public Optional<Credential> withHandle(final Handle handle) throws JdbiException {
        return handle.attach(CredentialDAO.class).selectByEmailAddress(emailAddress);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectCredentialByEmailAddress that = (SelectCredentialByEmailAddress) o;
        return emailAddress.equals(that.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    @Override
    public String toString() {
        return "SelectCredentialByEmailAddress{" +
                "emailAddress='" + emailAddress + '\'' +
                '}';
    }

}
