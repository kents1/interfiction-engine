package uk.co.engine.interfiction.module.account.repository.mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import uk.co.engine.interfiction.module.account.model.Account;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper implements RowMapper<Account> {

    @Override
    public Account map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Account(
                rs.getLong("id"),
                rs.getString("email_address")
        );
    }

}
