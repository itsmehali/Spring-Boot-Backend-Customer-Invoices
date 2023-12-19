package io.fintech.Fintech.repository;

import io.fintech.Fintech.domain.Category;

import java.util.Collection;

public interface CategoryRepository<T extends Category> {
    Collection<T> listAll();
}
