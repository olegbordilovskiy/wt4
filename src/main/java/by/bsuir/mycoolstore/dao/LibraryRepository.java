package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.UserFilmEntity;
import by.bsuir.mycoolstore.entity.UserFilmEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The LibraryRepository interface provides CRUD operations for accessing and manipulating user library data in the database.
 */
@Repository
public interface LibraryRepository extends CrudRepository<UserFilmEntity, UserFilmEntityPK> {

    /**
     * Retrieves the user's library based on the specified user ID.
     *
     * @param userId The ID of the user.
     * @return A list of UserFilmEntity representing the films in the user's library.
     */
    List<UserFilmEntity> getLibraryByUfUser(Long userId);
}
