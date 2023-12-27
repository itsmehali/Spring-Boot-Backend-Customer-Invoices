package io.fintech.Fintech.service.implementation;

import io.fintech.Fintech.domain.Product;
import io.fintech.Fintech.dto.ProductDTO;
import io.fintech.Fintech.dto.UserDTO;
import io.fintech.Fintech.repository.ProductRepository;
import io.fintech.Fintech.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import static org.springframework.data.domain.PageRequest.of;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository<Product> productRepository;

    @Override
    public Product createProduct(Product product, UserDTO userDTO) {
        product.setCreatedAt(LocalDateTime.now());
        return productRepository.create(product, userDTO);
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Page<Product> getProducts(int page, int size) {
        return null;
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.get(id);
    }

    @Override
    public int deleteProduct(Long id) {
        return productRepository.delete(id);
    }

    @Override
    public Page<ProductDTO> getProductsWithCategory(int page, int size, UserDTO userDTO) {
        return productRepository.findAll(of(page,size), userDTO);
    }
}
