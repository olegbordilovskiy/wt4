package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The UserRepository interface provides CRUD operations for accessing and manipulating user data in the database.
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    /**
     * Retrieves a list of users who have been banned.
     *
     * @return A list of UserEntity representing the banned users.
     */
    List<UserEntity> findByUsrBannedByIsNotNull();

    /**
     * Retrieves a user based on the specified email and password.
     *
     * @param usrEmail    The email of the user.
     * @param usrPassword The password of the user.
     * @return The UserEntity object representing the user, or null if not found.
     */
    UserEntity findByUsrEmailAndUsrPassword(String usrEmail, String usrPassword);
}
