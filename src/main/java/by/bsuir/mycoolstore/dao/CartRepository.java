package by.bsuir.mycoolstore.dao;

import by.bsuir.mycoolstore.entity.CartEntity;
import by.bsuir.mycoolstore.entity.CartEntityPK;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The CartRepository interface provides CRUD operations for accessing and manipulating cart data in the database.
 */
@Repository
public interface CartRepository extends CrudRepository<CartEntity, CartEntityPK> {

    /**
     * Deletes cart items based on the specified user ID.
     *
     * @param crtUser The ID of the user associated with the cart items.
     */
    void deleteByCrtUser(Long crtUser);

    /**
     * Retrieves cart items based on the specified user ID.
     *
     * @param userId The ID of the user.
     * @return A list of cart items associated with the user.
     */
    List<CartEntity> getCartByCrtUser(Long userId);
}
