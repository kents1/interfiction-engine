package uk.co.engine.interfiction.module.account.repository.command;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.HandleConsumer;
import org.jdbi.v3.core.JdbiException;
import uk.co.engine.interfiction.module.account.model.Credential;
import uk.co.engine.interfiction.module.account.repository.dao.CredentialDAO;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class InsertCredential implements HandleConsumer<JdbiException> {

    private final Credential credential;

    public InsertCredential(final Credential credential) {
        requireNonNull(credential);
        this.credential = credential;
    }

    @Override
    public void useHandle(final Handle handle) throws JdbiException {
        handle.attach(CredentialDAO.class).insertCredential(credential);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsertCredential that = (InsertCredential) o;
        return credential.equals(that.credential);
    }

    @Override
    public int hashCode() {
        return Objects.hash(credential);
    }

    @Override
    public String toString() {
        return "InsertCredential{" +
                "credential=" + credential +
                '}';
    }

}
