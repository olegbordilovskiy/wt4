package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.CategoryRepository;
import by.bsuir.mycoolstore.entity.CategoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The CategoryService class provides operations related to categories.
 */
@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    /**
     * Constructs a CategoryService instance.
     *
     * @param categoryRepository The repository for accessing category data.
     */
    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Retrieves all categories.
     *
     * @return A list of CategoryEntity objects representing the categories.
     */
    public List<CategoryEntity> getCategories() {
        return (List<CategoryEntity>) categoryRepository.findAll();
    }
}
