package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.FilmEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The FilmRepository interface provides CRUD operations for accessing and manipulating film data in the database.
 */
@Repository
public interface FilmRepository extends CrudRepository<FilmEntity, Long> {
}
