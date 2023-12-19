package io.fintech.Fintech.rowmapper;

import io.fintech.Fintech.domain.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Category.builder()
                .id(resultSet.getLong("category_id"))
                .categoryName(resultSet.getString("category_name"))
                .build();
    }
}