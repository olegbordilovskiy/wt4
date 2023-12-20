package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.FilmMediaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * The MediaRepository interface provides CRUD operations for accessing and manipulating film media data in the database.
 */
@Repository
public interface MediaRepository extends CrudRepository<FilmMediaEntity, Long> {
}
