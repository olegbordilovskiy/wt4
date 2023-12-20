package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The FeedbackEntity class represents a feedback entry in the database.
 */
@Entity
@Table(name = "feedback", schema = "mycoolstore")
public class FeedbackEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "fbk_id")
    private Long fbkId;

    /**
     * Gets the ID of the feedback entry.
     *
     * @return The ID of the feedback entry.
     */
    public Long getFbkId() {
        return fbkId;
    }

    /**
     * Sets the ID of the feedback entry.
     *
     * @param fbkId The ID of the feedback entry.
     */
    public void setFbkId(Long fbkId) {
        this.fbkId = fbkId;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fbk_author", referencedColumnName = "usr_id")
    private UserEntity fbkAuthor;

    /**
     * Gets the author of the feedback.
     *
     * @return The author of the feedback.
     */
    public UserEntity getFbkAuthor() {
        return fbkAuthor;
    }

    /**
     * Sets the author of the feedback.
     *
     * @param fbkAuthor The author of the feedback.
     */
    public void setFbkAuthor(UserEntity fbkAuthor) {
        this.fbkAuthor = fbkAuthor;
    }

    @Basic
    @Column(name = "fbk_film")
    private Long fbkFilm;

    /**
     * Gets the ID of the film associated with the feedback.
     *
     * @return The ID of the film.
     */
    public Long getFbkFilm() {
        return fbkFilm;
    }

    /**
     * Sets the ID of the film associated with the feedback.
     *
     * @param fbkFilm The ID of the film.
     */
    public void setFbkFilm(Long fbkFilm) {
        this.fbkFilm = fbkFilm;
    }

    @Basic
    @Column(name = "fbk_text")
    private String fbkText;

    /**
     * Gets the text content of the feedback.
     *
     * @return The text content of the feedback.
     */
    public String getFbkText() {
        return fbkText;
    }

    /**
     * Sets the text content of the feedback.
     *
     * @param fbkText The text content of the feedback.
     */
    public void setFbkText(String fbkText) {
        this.fbkText = fbkText;
    }

    @Basic
    @Column(name = "fbk_rating")
    private Short fbkRating;

    /**
     * Gets the rating of the feedback.
     *
     * @return The rating of the feedback.
     */
    public Short getFbkRating() {
        return fbkRating;
    }

    /**
     * Sets the rating of the feedback.
     *
     * @param fbkRating The rating of the feedback.
     */
    public void setFbkRating(Short fbkRating) {
        this.fbkRating = fbkRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FeedbackEntity that = (FeedbackEntity) o;

        if (!Objects.equals(fbkId, that.fbkId)) return false;
        if (!Objects.equals(fbkAuthor, that.fbkAuthor)) return false;
        if (!Objects.equals(fbkFilm, that.fbkFilm)) return false;
        if (!Objects.equals(fbkText, that.fbkText)) return false;
        return Objects.equals(fbkRating, that.fbkRating);
    }

    @Override
    public int hashCode() {
        int result = fbkId != null ? fbkId.hashCode() : 0;
        result = 31 * result + (fbkAuthor != null ? fbkAuthor.hashCode() : 0);
        result = 31 * result + (fbkFilm != null ? fbkFilm.hashCode() : 0);
        result = 31 * result + (fbkText != null ? fbkText.hashCode() : 0);
        result = 31 * result + (fbkRating != null ? fbkRating.hashCode() : 0);
        return result;
    }
}
