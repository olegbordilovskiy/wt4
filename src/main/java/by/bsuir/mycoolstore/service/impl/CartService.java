package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.CartRepository;
import by.bsuir.mycoolstore.dao.FilmRepository;
import by.bsuir.mycoolstore.dao.LibraryRepository;
import by.bsuir.mycoolstore.entity.CartEntity;
import by.bsuir.mycoolstore.entity.CartEntityPK;
import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.entity.UserFilmEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The CartService class provides operations related to the user's cart.
 */
@Service
@Transactional
public class CartService {
    private final CartRepository cartRepository;
    private final FilmRepository filmRepository;
    private final LibraryRepository libraryRepository;

    /**
     * Constructs a CartService instance.
     *
     * @param cartRepository    The repository for accessing cart data.
     * @param filmRepository    The repository for accessing film data.
     * @param libraryRepository The repository for accessing library data.
     */
    @Autowired
    public CartService(CartRepository cartRepository, FilmRepository filmRepository, LibraryRepository libraryRepository) {
        this.cartRepository = cartRepository;
        this.filmRepository = filmRepository;
        this.libraryRepository = libraryRepository;
    }

    /**
     * Retrieves the films in the user's cart.
     *
     * @param userId The ID of the user.
     * @return A list of FilmEntity objects representing the films in the user's cart.
     */
    public List<FilmEntity> getCartFilms(Long userId) {
        var carts = cartRepository.getCartByCrtUser(userId);

        return (List<FilmEntity>) filmRepository.findAllById(carts.stream().map(CartEntity::getCrtFilm)
                .collect(Collectors.toList()));
    }

    /**
     * Checks if a film is present in the user's cart.
     *
     * @param userId The ID of the user.
     * @param filmId The ID of the film.
     * @return true if the film is in the cart, false otherwise.
     */
    public boolean isInCart(Long userId, Long filmId) {
        var pk = new CartEntityPK();
        pk.setCrtUser(userId);
        pk.setCrtFilm(filmId);

        return cartRepository.findById(pk).isPresent();
    }

    /**
     * Saves a cart entity to the repository.
     *
     * @param cart The cart entity to be saved.
     */
    public void save(CartEntity cart) {
        cartRepository.save(cart);
    }

    /**
     * Performs the purchase of films in the user's cart.
     *
     * @param userId The ID of the user.
     */
    public void buy(Long userId) {
        var library = new ArrayList<UserFilmEntity>();
        var cart = cartRepository.getCartByCrtUser(userId);

        for (var c : cart) {
            var ufe = new UserFilmEntity();
            ufe.setUfFilm(c.getCrtFilm());
            ufe.setUfUser(c.getCrtUser());
            library.add(ufe);
        }

        libraryRepository.saveAll(library);

        cartRepository.deleteByCrtUser(userId);
    }

    /**
     * Removes the user's cart.
     *
     * @param userId The ID of the user.
     */
    public void remove(Long userId) {
        cartRepository.deleteByCrtUser(userId);
    }
}
