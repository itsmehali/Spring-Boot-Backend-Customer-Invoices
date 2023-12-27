package io.fintech.Fintech.query;

public class ProductQuery {
    public static final String INSERT_PRODUCT_QUERY = "INSERT INTO Products (product_name, user_id, category_id) VALUES (:productName, :userId, :categoryId)";
    public static final String SELECT_PRODUCTS_WITH_CATEGORIES_QUERY = "SELECT * FROM Products p JOIN Categories c ON p.category_id = c.category_id WHERE p.user_id = :userId ORDER BY p.id";
    public static final String DELETE_PRODUCT_BY_ID_QUERY = "DELETE FROM Products WHERE id = :productId";
    public static final String SELECT_PRODUCT_BY_ID_QUERY = "SELECT * FROM Products where id = :productId";

}
