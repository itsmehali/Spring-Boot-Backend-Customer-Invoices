package io.fintech.Fintech.service.implementation;

import io.fintech.Fintech.domain.Category;
import io.fintech.Fintech.repository.CategoryRepository;
import io.fintech.Fintech.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository<Category> categoryRepository;

    @Override
    public Collection<Category> listCategory() {
        return  categoryRepository.listAll();
    }
}
