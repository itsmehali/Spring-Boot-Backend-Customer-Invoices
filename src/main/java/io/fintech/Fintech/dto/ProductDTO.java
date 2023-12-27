package io.fintech.Fintech.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ProductDTO {
    private Long id;
    @NotNull(message = "Product name must not be null")
    @NotBlank(message = "Product name must not be blank")
    private String productName;
    @NotNull(message = "Category must not be null")
    private Long categoryId;
    //private String categoryName;
    private LocalDateTime createdAt;
}
