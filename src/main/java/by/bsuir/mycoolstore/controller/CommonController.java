package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.entity.FeedbackEntity;
import by.bsuir.mycoolstore.entity.UserEntity;
import by.bsuir.mycoolstore.entity.enums.Role;
import by.bsuir.mycoolstore.service.exception.ServiceException;
import by.bsuir.mycoolstore.service.impl.*;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * The CommonController class is responsible for handling common requests and actions related to the application.
 */
@Controller
@RequestMapping("/")
public class CommonController {
    private static final Logger logger = LogManager.getLogger(CommonController.class);
    private final FilmService filmService;
    private final UserService userService;
    private final FeedbackService feedbackService;
    private final CartService cartService;
    private final LibraryService libraryService;
    private final MediaService mediaService;

    /**
     * Constructs a CommonController with the specified services.
     *
     * @param fs  The FilmService to be used.
     * @param us  The UserService to be used.
     * @param fbs The FeedbackService to be used.
     * @param cs  The CartService to be used.
     * @param ls  The LibraryService to be used.
     * @param ms  The MediaService to be used.
     */
    @Autowired
    public CommonController(FilmService fs, UserService us, FeedbackService fbs, CartService cs, LibraryService ls,
                            MediaService ms) {
        this.filmService = fs;
        this.userService = us;
        this.feedbackService = fbs;
        this.cartService = cs;
        this.libraryService = ls;
        this.mediaService = ms;
    }

    /**
     * Handles the main page request.
     *
     * @return The ModelAndView for the main page.
     */
    @GetMapping("")
    public String mainPage(Model model) {
        var films = filmService.getFilms();
        model.addAttribute("films", films);

        logger.info("Main page GET");

        return "index";
    }

    /**
     * Handles the registration page request.
     *
     * @param model The Model object.
     * @return The registration page view name.
     */
    @GetMapping("Register")
    public String registrationPage(Model model) {
        var user = new UserEntity();

        model.addAttribute("user", user);
        logger.info("Registration GET");

        return "register";
    }

    /**
     * Handles the registration form submission.
     *
     * @param user    The UserEntity object containing the registration information.
     * @param request The HttpServletRequest object.
     * @return The redirection URL.
     */
    @PostMapping("Register")
    public String registration(@ModelAttribute("user") UserEntity user, HttpServletRequest request) {
        user.setUsrRole(Role.CUSTOMER.toString());
        user.setUsrPassword(UserEntity.getHashSha512Password(user.getUsrPassword()));
        try {
            Long id = userService.registration(user);
            request.getSession().setAttribute("userID", id);
            logger.info("Registration of " + user.getUsrEmail());
        } catch (ServiceException e) {
            logger.error("Registration of " + user.getUsrEmail() + " failed");
            return "redirect:/Error";
        }

        return "redirect:/";
    }

    /**
     * Handles the authorization page request.
     *
     * @param model The Map object containing the model attributes.
     * @return The authorization page view name.
     */
    @GetMapping("Authorization")
    public String authorisationPage(Model model) {
        var user = new UserEntity();

        model.addAttribute("user", user);

        logger.info("Authorization GET");

        return "authorization";
    }

    /**
     * Handles the authorization form submission.
     *
     * @param user    The UserEntity object containing the authorization information.
     * @param request The HttpServletRequest object.
     * @return The redirection URL.
     */
    @PostMapping("Authorization")
    public String authorization(@ModelAttribute("user") UserEntity user, HttpServletRequest request) {
        user.setUsrPassword(UserEntity.getHashSha512Password(user.getUsrPassword()));
        var signedUser = userService.signIn(user);

        if (signedUser.isPresent()) {
            var session = request.getSession();
            if (signedUser.get().getUsrRole().equalsIgnoreCase(Role.ADMIN.toString())) {
                session.setAttribute("isAdmin", signedUser.get().getUsrRole());
            }
            session.setAttribute("userID", signedUser.get().getUsrId());

            return "redirect:/";
        }

        return "error";
    }

    /**
     * Handles the film page request.
     *
     * @param filmId  The ID of the film.
     * @param request The HttpServletRequest object.
     * @param model   The Model object.
     * @return The ModelAndView for the film page.
     */
    @GetMapping("Film")
    public String filmPage(@RequestParam("filmId") Long filmId, HttpServletRequest request, Model model) {
        Long userId = (Long) request.getSession().getAttribute("userID");
        model.addAttribute("feedback", new FeedbackEntity());

        var isFilmInCart = Boolean.FALSE;
        var isUserBanned = Boolean.TRUE;
        var isPaid = Boolean.FALSE;

        if (userId != null) {
            isFilmInCart = cartService.isInCart(userId, filmId);
            isUserBanned = userService.isBanned(userId);
            isPaid = libraryService.isInLibrary(userId, filmId);
        }

        model.addAttribute("isFilmInCart", isFilmInCart);
        model.addAttribute("isBanned", isUserBanned);
        model.addAttribute("isPaid", isPaid);

        var film = filmService.getFilmById(filmId);
        var feedbacks = feedbackService.getFilmFeedbacks(filmId);
        var media = mediaService.getFilmMedia(filmId);

        film.ifPresent(filmEntity -> model.addAttribute("film", filmEntity));
        media.ifPresent(filmMediaEntity -> model.addAttribute("media", filmMediaEntity));
        model.addAttribute("feedbacks", feedbacks);

        logger.info("Film page GET");

        return "customerFilm";
    }

    /**
     * Handles the exit action.
     *
     * @param request The HttpServletRequest object.
     * @return The redirection URL.
     */
    @PostMapping("Exit")
    public String exit(HttpServletRequest request) {
        var session = request.getSession();

        session.removeAttribute("userID");
        session.removeAttribute("isAdmin");

        logger.info("Exiting");

        return "redirect:/";
    }

    /**
     * Handles the language change action.
     *
     * @param request The HttpServletRequest object.
     * @return The redirection URL.
     */
    @PostMapping("Language")
    public String changeLanguage(HttpServletRequest request) {
        var session = request.getSession();

        if (session.getAttribute("lang") != null) {
            session.removeAttribute("lang");
        } else {
            session.setAttribute("lang", "ru");
        }

        logger.info("Changing language");

        return "redirect:/";
    }

    /**
     * Handles the error page request.
     *
     * @return The ModelAndView for the error page.
     */
    @GetMapping("Error")
    public String error() {
        return "error";
    }
}
