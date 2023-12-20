package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The CategoryRepository interface provides CRUD operations for accessing and manipulating category data in the database.
 */
@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
}
