package by.bsuir.mycoolstore.controller;

import by.bsuir.mycoolstore.config.file.FileConfig;
import by.bsuir.mycoolstore.entity.CategoryEntity;
import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.entity.FilmMediaEntity;
import by.bsuir.mycoolstore.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code AdminController} class handles the administrative operations for the application.
 */
@Controller
@RequestMapping("/Admin/")
public class AdminController {
    private static final Logger logger = LogManager.getLogger(AdminController.class);
    private final CategoryService categoryService;
    private final FilmService filmService;
    private final UserService userService;
    private final MediaService mediaService;

    @Autowired
    public AdminController(CategoryService cs, FilmService fs, UserService us, MediaService ms) {
        this.categoryService = cs;
        this.filmService = fs;
        this.userService = us;
        this.mediaService = ms;
    }

    /**
     * Handles the GET request for the add film page.
     *
     * @param model The {@link Model} to add attributes to.
     * @return The {@link ModelAndView} for the add film page.
     */
    @GetMapping("AddFilm")
    public String addPage(Model model) {
        var ageRestrictions = AgeRestrictionService.getAgeRestrictions();
        var categories = categoryService.getCategories();

        var film = new FilmEntity();
        film.setCategories(new ArrayList<>());
        film.setFlmId(0L);
        film.setFlmAge("");
        film.setFlmAuthor("");
        film.setFlmDescription("");
        film.setFlmDiscount((short) 0);
        film.setFlmName("");
        film.setFlmPrice(BigDecimal.ZERO);
        model.addAttribute("film", film);

        model.addAttribute("command", "AddFilm");
        model.addAttribute("film", film);
        model.addAttribute("ageRestrictions", ageRestrictions);
        model.addAttribute("categories", categories);

        logger.info("Film " + film + " added");

        return "adminFilm";
    }

    /**
     * Handles the GET request for the edit film page.
     *
     * @param filmId The ID of the film to edit.
     * @param model  The {@link Model} to add attributes to.
     * @return The {@link ModelAndView} for the edit film page.
     */
    @GetMapping("EditFilm")
    public String editPage(@RequestParam("filmId") Long filmId, Model model) {
        FilmEntity film;

        var ageRestrictions = AgeRestrictionService.getAgeRestrictions();
        var categories = categoryService.getCategories();
        var filmOpt = filmService.getFilmById(filmId);

        if (filmOpt.isPresent()) {
            film = filmOpt.get();
            ageRestrictions.remove(film.getFlmAge());
            categories.removeIf(c1 -> film.getCategories().stream().anyMatch(c2 -> c2.getCatName().equals(c1.getCatName())));
            model.addAttribute("film", film);

            model.addAttribute("command", "EditFilm");
            model.addAttribute("film", film);
            model.addAttribute("ageRestrictions", ageRestrictions);
            model.addAttribute("categories", categories);

            logger.info("Edit page GET");
        } else {
            logger.error("Editing " + filmId + " failed");
            return "error";
        }

        return "adminFilm";
    }

    /**
     * Handles the GET request for the ban list page.
     *
     * @return The {@link ModelAndView} for the ban list page.
     */
    @GetMapping("BanList")
    public String banListPage(Model model) {
        var users = userService.getBannedUsers();
        model.addAttribute("users", users);

        logger.info("Ban list GET");

        return "banList";
    }

    /**
     * Handles the POST request for adding a film.
     *
     * @param film        The film entity to add.
     * @param filmFile    The film file to upload.
     * @param trailerFile The trailer file to upload.
     * @param categories  The list of category IDs for the film.
     * @return The redirect URL after adding the film.
     */
    @PostMapping(value = "AddFilm", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addFilm(
            @ModelAttribute("film") FilmEntity film,
            @RequestPart("filmFile") MultipartFile filmFile,
            @RequestPart("trailerFile") MultipartFile trailerFile,
            @RequestParam("filmCategory") List<Long> categories
    ) {
        addCategories(film, categories);

        var addedFilm = filmService.save(film);

        String filmFilename = Instant.now().toEpochMilli() + filmFile.getOriginalFilename();
        String trailerFilename = Instant.now().toEpochMilli() + trailerFile.getOriginalFilename();

        var media = new FilmMediaEntity();
        media.setFmId(addedFilm.getFlmId());
        media.setFmFilmPath(filmFilename);
        media.setFmTrailerPath(trailerFilename);
        mediaService.save(media);

        try {
            saveFile(filmFile, FileConfig.VIDEO_DIRECTORY_PATH + FileConfig.FILM_DIR + File.separator + filmFilename);
            saveFile(trailerFile, FileConfig.VIDEO_DIRECTORY_PATH + FileConfig.TRAILER_DIR + File.separator +
                    trailerFilename);
            logger.info("Film added");
        } catch (IOException e) {
            logger.info("Adding film failed");
            return "redirect:/Error";
        }

        return "redirect:/";
    }

    /**
     * Handles the POST request for editing a film.
     *
     * @param film       The film entity to edit.
     * @param categories The list of category IDs for the film.
     * @return The redirect URL after editing the film.
     */
    @PostMapping("EditFilm")
    public String editFilm(@ModelAttribute("film") FilmEntity film, @RequestParam("filmCategory") List<Long> categories) {
        addCategories(film, categories);

        filmService.save(film);
        logger.info(film + " edited");

        return "redirect:/";
    }

    /**
     * Adds the specified categories to the film entity.
     *
     * @param film       The film entity to add categories to.
     * @param categories The list of category IDs to add.
     */
    private void addCategories(FilmEntity film, List<Long> categories) {
        var dbCategories = new ArrayList<CategoryEntity>();

        for (var cat : categories) {
            var c = new CategoryEntity();
            c.setCatId(cat);
            dbCategories.add(c);
        }

        film.setCategories(dbCategories);
    }

    /**
     * Saves the uploaded file to the specified file path.
     *
     * @param file     The file to save.
     * @param filePath The file path to save the file to.
     * @throws IOException If an I/O error occurs while saving the file.
     */
    private void saveFile(MultipartFile file, String filePath) throws IOException {
        try {
            Path targetPath = Path.of(filePath);
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Failed to save file: " + filePath, e);
        }
    }

    /**
     * Handles the POST request for unbanning a user.
     *
     * @param userId The ID of the user to unban.
     * @return The redirect URL after unbanning the user.
     */
    @PostMapping("Unban")
    public String unban(@RequestParam("userId") Long userId) {
        userService.unban(userId);

        logger.info(userId + " unbanned");

        return "redirect:/Admin/BanList";
    }

    /**
     * Handles the POST request for banning a user.
     *
     * @param userId  The ID of the user to ban.
     * @param adminId The ID of the admin performing the ban.
     * @return The redirect URL after banning the user.
     */
    @PostMapping("Ban")
    public String ban(@RequestParam("authorId") Long userId, @SessionAttribute("userID") Long adminId) {
        userService.ban(userId, adminId);

        logger.info(userId + " banned");

        return "redirect:/Admin/BanList";
    }
}
