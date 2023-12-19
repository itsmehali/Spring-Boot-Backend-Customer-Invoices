package io.fintech.Fintech.repository;

import io.fintech.Fintech.domain.Product;
import io.fintech.Fintech.dto.ProductDTO;
import io.fintech.Fintech.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Collection;

public interface ProductRepository<T extends Product> {
    T create(T data, UserDTO userDTO);
    Collection<T> list(int page, int pageSize);
    T get(Long id);
    T update(T data);
    T delete(T data);
    int delete(Long id);
    Page<ProductDTO> findAll(PageRequest of, UserDTO userDTO);

}
