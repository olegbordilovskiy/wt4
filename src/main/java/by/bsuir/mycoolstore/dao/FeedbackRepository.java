package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.FeedbackEntity;
import by.bsuir.mycoolstore.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The FeedbackRepository interface provides CRUD operations for accessing and manipulating feedback data in the database.
 */
@Repository
public interface FeedbackRepository extends CrudRepository<FeedbackEntity, Long> {

    /**
     * Retrieves a list of feedback entities based on the specified film ID.
     *
     * @param fbkFilm The ID of the film.
     * @return A list of feedback entities associated with the film.
     */
    List<FeedbackEntity> findByFbkFilm(Long fbkFilm);

    /**
     * Deletes all feedback entities associated with the specified user.
     *
     * @param user The UserEntity object representing the user.
     */
    void deleteAllByFbkAuthor(UserEntity user);
}
