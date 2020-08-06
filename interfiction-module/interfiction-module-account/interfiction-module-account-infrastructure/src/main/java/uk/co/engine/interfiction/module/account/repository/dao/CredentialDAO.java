package uk.co.engine.interfiction.module.account.repository.dao;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import uk.co.engine.interfiction.module.account.model.Credential;
import uk.co.engine.interfiction.module.account.repository.mapper.CredentialRowMapper;

import java.util.Optional;

public interface CredentialDAO {

    @SqlQuery(Sql.SELECT_BY_EMAIL_ADDRESS)
    @RegisterRowMapper(CredentialRowMapper.class)
    Optional<Credential> selectByEmailAddress(@Bind("emailAddress") final String emailAddress);

    @SqlUpdate(Sql.INSERT_CREDENTIAL)
    void insertCredential(@BindBean Credential credential);

    interface Sql {

        String SELECT_BY_EMAIL_ADDRESS = """
                SELECT * FROM credential c WHERE c.email_address = :emailAddress
                """;

        String INSERT_CREDENTIAL = """
                INSERT INTO credential (email_address, password)
                VALUES (:emailAddress, :password)
                """;

    }

}
