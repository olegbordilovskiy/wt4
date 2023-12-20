package by.bsuir.mycoolstore.service.exception;

import java.io.Serial;

/**
 * Exception class for service layer related issues.
 */
public class ServiceException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ServiceException with no detail message.
     */
    public ServiceException() {
        super();
    }

    /**
     * Constructs a new ServiceException with the specified detail message.
     *
     * @param message the detail message
     */
    public ServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new ServiceException with the specified cause.
     *
     * @param e the cause (which is saved for later retrieval by the getCause() method)
     */
    public ServiceException(Exception e) {
        super(e);
    }

    /**
     * Constructs a new ServiceException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param e       the cause (which is saved for later retrieval by the getCause() method)
     */
    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
