package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.FilmRepository;
import by.bsuir.mycoolstore.entity.FilmEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The FilmService class provides operations related to films.
 */
@Service
@Transactional
public class FilmService {
    private final FilmRepository filmRepository;

    /**
     * Constructs a FilmService instance.
     *
     * @param filmRepository The repository for accessing film data.
     */
    @Autowired
    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    /**
     * Saves a film entity to the repository.
     *
     * @param film The film entity to be saved.
     * @return The saved FilmEntity object.
     */
    public FilmEntity save(FilmEntity film) {
        return filmRepository.save(film);
    }

    /**
     * Retrieves all films.
     *
     * @return A list of FilmEntity objects representing the films.
     */
    public List<FilmEntity> getFilms() {
        return (List<FilmEntity>) filmRepository.findAll();
    }

    /**
     * Retrieves a film by its ID.
     *
     * @param id The ID of the film.
     * @return An Optional containing the FilmEntity object if found, or an empty Optional if not found.
     */
    public Optional<FilmEntity> getFilmById(Long id) {
        return filmRepository.findById(id);
    }
}
