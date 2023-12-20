package by.bsuir.mycoolstore.service.impl;

import by.bsuir.mycoolstore.dao.FeedbackRepository;
import by.bsuir.mycoolstore.dao.UserRepository;
import by.bsuir.mycoolstore.entity.UserEntity;
import by.bsuir.mycoolstore.service.exception.ServiceException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The UserService class provides operations related to user management.
 */
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final FeedbackRepository feedbackRepository;

    /**
     * Constructs a UserService instance.
     *
     * @param userRepository     The repository for accessing user data.
     * @param feedbackRepository The repository for accessing feedback data.
     */
    @Autowired
    public UserService(UserRepository userRepository, FeedbackRepository feedbackRepository) {
        this.userRepository = userRepository;
        this.feedbackRepository = feedbackRepository;
    }

    /**
     * Checks if a user is banned.
     *
     * @param userId The ID of the user.
     * @return true if the user is banned, false otherwise.
     */
    public Boolean isBanned(Long userId) {
        var user = userRepository.findById(userId);

        return user.map(userEntity -> userEntity.getUsrBannedBy() != null).orElse(Boolean.FALSE);
    }

    /**
     * Registers a new user.
     *
     * @param user The user entity to be registered.
     * @return The ID of the registered user.
     * @throws ServiceException if the password length is less than 8 or if the user is banned.
     */
    public Long registration(UserEntity user) throws ServiceException {
        UserEntity savedUser;

        if (user.getUsrPassword().length() < 8) {
            throw new ServiceException("Password length is less than 8");
        }

        if (user.getUsrBannedBy() != null) {
            throw new ServiceException("Registration of banned user");
        }

        try {
            savedUser = userRepository.save(user);
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

        return savedUser.getUsrId();
    }

    /**
     * Retrieves a list of banned users.
     *
     * @return A list of UserEntity objects representing the banned users.
     */
    public List<UserEntity> getBannedUsers() {
        return userRepository.findByUsrBannedByIsNotNull();
    }

    /**
     * Performs user sign-in.
     *
     * @param user The user entity for sign-in.
     * @return An Optional containing the UserEntity object if sign-in is successful, or an empty Optional if not.
     */
    public Optional<UserEntity> signIn(UserEntity user) {
        return Optional.ofNullable(userRepository.findByUsrEmailAndUsrPassword(user.getUsrEmail(), user.getUsrPassword()));
    }

    /**
     * Bans a user.
     *
     * @param userId  The ID of the user to be banned.
     * @param adminId The ID of the admin performing the ban.
     */
    public void ban(Long userId, Long adminId) {
        var user = userRepository.findById(userId);

        if (user.isPresent()) {
            user.get().setUsrBannedBy(adminId);
            feedbackRepository.deleteAllByFbkAuthor(user.get());
        }
    }

    /**
     * Unbans a user.
     *
     * @param userId The ID of the user to be unbanned.
     */
    public void unban(Long userId) {
        var user = userRepository.findById(userId);

        user.ifPresent(userEntity -> userEntity.setUsrBannedBy(null));
    }
}
