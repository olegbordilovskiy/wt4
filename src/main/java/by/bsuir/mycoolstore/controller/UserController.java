package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.CartEntity;
import by.bsuir.mycoolstore.entity.FeedbackEntity;
import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.entity.UserEntity;
import by.bsuir.mycoolstore.service.exception.ServiceException;
import by.bsuir.mycoolstore.service.impl.CartService;
import by.bsuir.mycoolstore.service.impl.FeedbackService;
import by.bsuir.mycoolstore.service.impl.LibraryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * The UserController class is responsible for handling user-related requests and actions.
 */
@Controller
@RequestMapping("/User/")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    private final LibraryService libraryService;
    private final CartService cartService;
    private final FeedbackService feedbackService;

    /**
     * Constructs a UserController with the specified services.
     *
     * @param ls  The LibraryService to be used.
     * @param cs  The CartService to be used.
     * @param fbs The FeedbackService to be used.
     */
    public UserController(LibraryService ls, CartService cs, FeedbackService fbs) {
        this.libraryService = ls;
        this.cartService = cs;
        this.feedbackService = fbs;
    }

    /**
     * Handles the library page request.
     *
     * @param userId The ID of the user.
     * @return The ModelAndView for the library page.
     */
    @GetMapping("Library")
    public String libraryPage(@SessionAttribute("userID") Long userId, Model model) {
        var films = libraryService.getUserFilms(userId);
        model.addAttribute("films", films);

        logger.info("Library GET");

        return "library";
    }

    /**
     * Handles the cart page request.
     *
     * @param userId The ID of the user.
     * @return The ModelAndView for the cart page.
     */
    @GetMapping("Cart")
    public String cartPage(@SessionAttribute("userID") Long userId, Model model) {
        BigDecimal total = BigDecimal.ZERO;

        var films = cartService.getCartFilms(userId);

        for (FilmEntity film : films) {
            total = total.add(film.getRealPrice());
        }

        model.addAttribute("films", films);
        model.addAttribute("total", total);

        logger.info("Cart GET");

        return "cart";
    }

    /**
     * Handles the removal of items from the cart.
     *
     * @param userId The ID of the user.
     * @return The redirection URL.
     */
    @PostMapping("Cart/Remove")
    public String remove(@SessionAttribute("userID") Long userId) {
        cartService.remove(userId);

        logger.info("Removing from cart");

        return "redirect:/User/Cart";
    }

    /**
     * Handles the purchase of items in the cart.
     *
     * @param userId The ID of the user.
     * @return The redirection URL.
     */
    @PostMapping("Cart/Buy")
    public String buy(@SessionAttribute("userID") Long userId) {
        cartService.buy(userId);

        logger.info(userId + " bought films");

        return "redirect:/";
    }

    /**
     * Handles the addition of an item to the cart.
     *
     * @param filmId The ID of the film.
     * @param userId The ID of the user.
     * @return The redirection URL.
     */
    @PostMapping("Cart/Add")
    public String addToCart(@RequestParam("filmID") Long filmId, @SessionAttribute("userID") Long userId) {
        var cartItem = new CartEntity();
        cartItem.setCrtUser(userId);
        cartItem.setCrtFilm(filmId);

        cartService.save(cartItem);

        logger.info("Adding " + filmId + " to " + userId + " cart");

        return "redirect:/User/Cart";
    }

    /**
     * Handles the submission of feedback for a film.
     *
     * @param feedback The FeedbackEntity object containing the feedback information.
     * @param userId   The ID of the user.
     * @return The redirection URL.
     */
    @PostMapping("Feedback")
    public String leaveFeedback(@ModelAttribute("feedback") FeedbackEntity feedback, @SessionAttribute("userID") Long userId) {
        var author = new UserEntity();
        author.setUsrId(userId);

        feedback.setFbkAuthor(author);

        try {
            feedbackService.save(feedback);
        } catch (ServiceException e) {
            logger.error("Failed adding feedback " + feedback);
            return "redirect:/Error";
        }

        return "redirect:/Film?filmId=" + feedback.getFbkFilm();
    }
}
