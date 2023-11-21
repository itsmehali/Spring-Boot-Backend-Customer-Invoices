package io.fintech.Fintech.rowmapper;

import io.fintech.Fintech.domain.UserEvent;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserEventRowMapper implements RowMapper<UserEvent> {
    @Override
    public UserEvent mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return UserEvent.builder()
                .id(resultSet.getLong("id"))
                .type(resultSet.getString("type"))
                .description(resultSet.getString("description"))
                .type(resultSet.getString("device"))
                .description(resultSet.getString("ip_address"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
