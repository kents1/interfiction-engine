package uk.co.engine.interfiction.module.account.repository.mapper;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import uk.co.engine.interfiction.module.account.model.Credential;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CredentialRowMapper implements RowMapper<Credential> {

    @Override
    public Credential map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Credential(
                rs.getString("email_address"),
                rs.getString("password")
        );
    }

}
