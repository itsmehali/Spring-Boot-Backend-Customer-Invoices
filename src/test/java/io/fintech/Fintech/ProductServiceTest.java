package io.fintech.Fintech;

import io.fintech.Fintech.domain.Product;
import io.fintech.Fintech.dto.UserDTO;
import io.fintech.Fintech.exception.ApiException;
import io.fintech.Fintech.repository.ProductRepository;
import io.fintech.Fintech.service.implementation.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
    @Mock
    private ProductRepository<Product> productRepository;
    @Mock
    private JdbcTemplate jdbcTemplate;
    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private UserDTO userDTO;
    private String productName;
    private int categoryId;

    @BeforeEach()
    void init() {
         product = new Product();
         userDTO = new UserDTO();
         productName="productname";
         categoryId=1;
    }

    @DisplayName("Product Object created")
    @Test
    void testCreateProduct_whenProductDetailsProvided_returnsProductObject() {
        // Arrange
        when(productRepository.create(any(Product.class), any(UserDTO.class))).thenReturn(product);
        // Act
        Product createdProduct = productService.createProduct(product,userDTO);

        // Assert
        assertNotNull(createdProduct, "The createProduct() should not have returned null");
        assertEquals(product, createdProduct, "The returned product should be the same as the one returned by the repository");
        verify(productRepository).create(any(Product.class), any(UserDTO.class));
    }

    @DisplayName("Create Product - Exception Test")
    @Test
    void testCreateProduct_whenProductIsInvalid_throwsAPIException() {
        // Arrange
        String invalidProductName = "";

        product.setProductName(invalidProductName);
        String expectedExceptionMessage = "An error occurred. Please try again";

        // Act & Assert
        when(productRepository.create(eq(product), any(UserDTO.class))).thenThrow(new ApiException(expectedExceptionMessage));


        ApiException thrown = Assertions.assertThrows(ApiException.class, ()-> {
            productService.createProduct(product, userDTO);
        }, "Productname should not be empty");

        // Assert
        assertEquals(expectedExceptionMessage, thrown.getMessage(),
                "Exception error message is not correct");

        verify(productRepository).create(any(Product.class), any(UserDTO.class));

    }
}
