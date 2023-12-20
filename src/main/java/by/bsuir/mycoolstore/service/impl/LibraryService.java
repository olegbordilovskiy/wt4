package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.FilmRepository;
import by.bsuir.mycoolstore.dao.LibraryRepository;
import by.bsuir.mycoolstore.entity.FilmEntity;
import by.bsuir.mycoolstore.entity.UserFilmEntity;
import by.bsuir.mycoolstore.entity.UserFilmEntityPK;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The LibraryService class provides operations related to the user's film library.
 */
@Service
@Transactional
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final FilmRepository filmRepository;

    /**
     * Constructs a LibraryService instance.
     *
     * @param libraryRepository The repository for accessing library data.
     * @param filmRepository    The repository for accessing film data.
     */
    @Autowired
    public LibraryService(LibraryRepository libraryRepository, FilmRepository filmRepository) {
        this.libraryRepository = libraryRepository;
        this.filmRepository = filmRepository;
    }

    /**
     * Retrieves the films in the user's library.
     *
     * @param userId The ID of the user.
     * @return A list of FilmEntity objects representing the films in the user's library.
     */
    public List<FilmEntity> getUserFilms(Long userId) {
        var library = libraryRepository.getLibraryByUfUser(userId);

        return (List<FilmEntity>) filmRepository.findAllById(library.stream().map(UserFilmEntity::getUfFilm)
                .collect(Collectors.toList()));
    }

    /**
     * Checks if a film is present in the user's library.
     *
     * @param userId The ID of the user.
     * @param filmId The ID of the film.
     * @return true if the film is in the library, false otherwise.
     */
    public Boolean isInLibrary(Long userId, Long filmId) {
        var pk = new UserFilmEntityPK();
        pk.setUfUser(userId);
        pk.setUfFilm(filmId);

        return libraryRepository.findById(pk).isPresent();
    }
}
