package io.fintech.Fintech.repository.implementation;

import io.fintech.Fintech.domain.Product;
import io.fintech.Fintech.dto.ProductDTO;
import io.fintech.Fintech.dto.UserDTO;
import io.fintech.Fintech.exception.ApiException;
import io.fintech.Fintech.repository.ProductRepository;
import io.fintech.Fintech.rowmapper.ProductCategoryRowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Collection;

import static java.util.Map.of;

import static io.fintech.Fintech.query.ProductQuery.*;
import static java.util.Objects.requireNonNull;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProductRepositoryImpl implements ProductRepository<Product> {
    private final NamedParameterJdbcTemplate jdbc;


    @Override
    public Product create(Product product, UserDTO userDTO) {
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(product, userDTO);
            jdbc.update(INSERT_PRODUCT_QUERY,parameters, holder);
            product.setId(requireNonNull(holder.getKey()).longValue());
            return product;
        } catch (Exception exception) {
            throw new ApiException("An error occurred. Please try again");
        }
    }

    @Override
    public Collection<Product> list(int page, int pageSize) {
        return null;
    }

    @Override
    public Product get(Long id) {
        return null;
    }

    @Override
    public Product update(Product data) {
        return null;
    }

    @Override
    public Product delete(Product data) {
        return null;
    }

    @Override
    public int delete(Long id) {
        log.info("Deleting Product");
        try {
            return jdbc.update(DELETE_PRODUCT_BY_ID_QUERY, of("productId", id));
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again");
        }
    }

    @Override
    public Page<ProductDTO> findAll(PageRequest of, UserDTO userDTO) {
        log.info("Fetching all Products with categories");
        try {
            return new PageImpl<>(jdbc.query(SELECT_PRODUCTS_WITH_CATEGORIES_QUERY, of("userId", userDTO.getId()), new ProductCategoryRowMapper()));
        } catch (Exception exception) {
            log.error(exception.getMessage());
            throw new ApiException("An error occurred. Please try again");
        }
    }

    private SqlParameterSource getSqlParameterSource(Product product, UserDTO userDTO) {
        return new MapSqlParameterSource()
                .addValue("productName", product.getProductName())
                .addValue("userId", userDTO.getId())
                .addValue("categoryId", product.getCategoryId());
    }
}
