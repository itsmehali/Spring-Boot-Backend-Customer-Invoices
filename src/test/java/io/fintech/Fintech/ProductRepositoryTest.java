package io.fintech.Fintech;

import io.fintech.Fintech.domain.Product;
import io.fintech.Fintech.dto.UserDTO;
import io.fintech.Fintech.exception.ApiException;
import io.fintech.Fintech.repository.implementation.ProductRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)

public class ProductRepositoryTest {
    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ProductRepositoryImpl productRepository;


    @Test
    void testCreateProduct_whenProductIsInvalid_throwsApiException() {
        // Arrange
        String invalidProductName = "";
        Product product = new Product();
        product.setProductName(invalidProductName);
        UserDTO userDTO = new UserDTO();
        String expectedExceptionMessage = "An error occurred. Please try again";

        // Act & Assert
        ApiException thrown = assertThrows(ApiException.class, () -> {
            productRepository.create(product, userDTO);
        }, "Productname should not be empty");

        // Assert
        assertEquals(expectedExceptionMessage, thrown.getMessage(),
                "Exception error message is not correct");
    }
}
