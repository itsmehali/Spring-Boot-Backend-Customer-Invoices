package io.fintech.Fintech.rowmapper;

import io.fintech.Fintech.domain.Category;
import io.fintech.Fintech.domain.Product;
import io.fintech.Fintech.dto.CategoryDTO;
import io.fintech.Fintech.dto.ProductDTO;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryRowMapper implements RowMapper<ProductDTO> {


    @Override
    public ProductDTO mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return ProductDTO.builder()
                .id(resultSet.getLong("id"))
                .productName(resultSet.getString("product_name"))
                .categoryName(resultSet.getString("category_name"))
                .categoryId(resultSet.getLong("category_id"))
                .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime())
                .build();
    }
}
