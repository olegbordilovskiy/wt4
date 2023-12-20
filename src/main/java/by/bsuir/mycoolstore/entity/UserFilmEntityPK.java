package by.bsuir.mycoolstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

/**
 * The UserFilmEntityPK class represents the primary key for the UserFilmEntity class.
 */
public class UserFilmEntityPK implements Serializable {
    @Id
    @Column(name = "uf_user")
    private Long ufUser;

    /**
     * Gets the ID of the user associated with the film.
     *
     * @return The ID of the user.
     */
    public Long getUfUser() {
        return ufUser;
    }

    /**
     * Sets the ID of the user associated with the film.
     *
     * @param ufUser The ID of the user.
     */
    public void setUfUser(Long ufUser) {
        this.ufUser = ufUser;
    }

    @Id
    @Column(name = "uf_film")
    private Long ufFilm;

    /**
     * Gets the ID of the film associated with the user.
     *
     * @return The ID of the film.
     */
    public Long getUfFilm() {
        return ufFilm;
    }

    /**
     * Sets the ID of the film associated with the user.
     *
     * @param ufFilm The ID of the film.
     */
    public void setUfFilm(Long ufFilm) {
        this.ufFilm = ufFilm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserFilmEntityPK that = (UserFilmEntityPK) o;

        if (!Objects.equals(ufUser, that.ufUser)) return false;
        return Objects.equals(ufFilm, that.ufFilm);
    }

    @Override
    public int hashCode() {
        int result = ufUser != null ? ufUser.hashCode() : 0;
        result = 31 * result + (ufFilm != null ? ufFilm.hashCode() : 0);
        return result;
    }
}
