package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.FeedbackRepository;
import by.bsuir.mycoolstore.entity.FeedbackEntity;
import by.bsuir.mycoolstore.service.exception.ServiceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The FeedbackService class provides operations related to film feedbacks.
 */
@Service
@Transactional
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    /**
     * Constructs a FeedbackService instance.
     *
     * @param feedbackRepository The repository for accessing feedback data.
     */
    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Retrieves the feedbacks for a specific film.
     *
     * @param filmId The ID of the film.
     * @return A list of FeedbackEntity objects representing the feedbacks for the film.
     */
    public List<FeedbackEntity> getFilmFeedbacks(Long filmId) {
        return feedbackRepository.findByFbkFilm(filmId);
    }

    /**
     * Saves a feedback entity to the repository.
     *
     * @param feedback The feedback entity to be saved.
     * @throws ServiceException if the rating is invalid (not between 0 and 10 inclusive).
     */
    public void save(FeedbackEntity feedback) throws ServiceException {
        if (feedback.getFbkRating() > 10 || feedback.getFbkRating() < 0) {
            throw new ServiceException("Invalid rating");
        }
        feedbackRepository.save(feedback);
    }
}
