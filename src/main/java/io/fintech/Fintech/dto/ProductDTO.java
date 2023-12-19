package io.fintech.Fintech.dto;

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
    private String productName;
    private Long categoryId;
    private String categoryName;
    private LocalDateTime createdAt;
}
