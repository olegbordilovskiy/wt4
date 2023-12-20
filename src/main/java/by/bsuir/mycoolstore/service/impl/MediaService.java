package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.MediaRepository;
import by.bsuir.mycoolstore.entity.FilmMediaEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The MediaService class provides operations related to media.
 */
@Service
@Transactional
public class MediaService {
    private final MediaRepository mediaRepository;

    /**
     * Constructs a MediaService instance.
     *
     * @param mediaRepository The repository for accessing media data.
     */
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    /**
     * Retrieves the media for a specific film.
     *
     * @param filmId The ID of the film.
     * @return An Optional containing the FilmMediaEntity object if found, or an empty Optional if not found.
     */
    public Optional<FilmMediaEntity> getFilmMedia(Long filmId) {
        return mediaRepository.findById(filmId);
    }

    /**
     * Saves a film media entity to the repository.
     *
     * @param filmMedia The film media entity to be saved.
     */
    public void save(FilmMediaEntity filmMedia) {
        mediaRepository.save(filmMedia);
    }
}
