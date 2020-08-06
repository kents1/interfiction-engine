package uk.co.engine.interfiction.module.account.repository.dao;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import uk.co.engine.interfiction.module.account.model.Account;
import uk.co.engine.interfiction.module.account.repository.mapper.AccountRowMapper;

import java.util.Optional;

public interface AccountDAO {

    @SqlQuery(AccountDAO.Sql.SELECT_BY_EMAIL_ADDRESS)
    @RegisterRowMapper(AccountRowMapper.class)
    Optional<Account> selectByEmailAddress(@Bind("emailAddress") final String emailAddress);

    interface Sql {

        String SELECT_BY_EMAIL_ADDRESS = """
                SELECT * FROM account a WHERE a.email_address = :emailAddress
                """;

    }

}
