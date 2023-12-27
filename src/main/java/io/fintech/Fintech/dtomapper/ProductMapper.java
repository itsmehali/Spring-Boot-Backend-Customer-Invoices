package io.fintech.Fintech.dtomapper;

import io.fintech.Fintech.domain.Product;
import io.fintech.Fintech.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(target = "id", ignore = true) // Ignore ID field in the DTO
    Product toEntity(ProductDTO productDTO);

    ProductDTO toDTO(Product product);
}
