package io.fintech.Fintech.repository.implementation;

import io.fintech.Fintech.domain.Category;
import io.fintech.Fintech.dto.CategoryDTO;
import io.fintech.Fintech.exception.ApiException;
import io.fintech.Fintech.repository.CategoryRepository;
import io.fintech.Fintech.rowmapper.CategoryRowMapper;
import io.fintech.Fintech.rowmapper.RoleRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static io.fintech.Fintech.query.CategoryQuery.*;
import static io.fintech.Fintech.query.RoleQuery.SELECT_ROLES_QUERY;

@Repository
@RequiredArgsConstructor
@Slf4j
public class CategoryRepositoryImpl implements CategoryRepository<Category> {
    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Collection<Category> listAll() {
        log.info("Fetching all roles");
        try {
            return jdbc.query(SELECT_CATEGORIES_QUERY,  new CategoryRowMapper());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again");
        }
    }
}
