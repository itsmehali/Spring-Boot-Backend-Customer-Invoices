package io.fintech.Fintech.service;

import io.fintech.Fintech.domain.Product;
import io.fintech.Fintech.dto.ProductDTO;
import io.fintech.Fintech.dto.UserDTO;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product createProduct(Product product, UserDTO userDTO);
    Product updateProduct(Product product);
    Page<Product> getProducts(int page, int size);
    Product getProduct(Long id);
    int deleteProduct(Long id);

    Page<ProductDTO> getProductsWithCategory(int page, int size, UserDTO userDTO);
}
