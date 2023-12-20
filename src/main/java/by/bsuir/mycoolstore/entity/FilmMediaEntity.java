package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.util.Objects;

/**
 * The FilmMediaEntity class represents media information for a film in the database.
 */
@Entity
@Table(name = "film_media", schema = "mycoolstore")
public class FilmMediaEntity {
    @Id
    @Column(name = "fm_id")
    private Long fmId;

    /**
     * Gets the ID of the film media.
     *
     * @return The ID of the film media.
     */
    public Long getFmId() {
        return fmId;
    }

    /**
     * Sets the ID of the film media.
     *
     * @param fmId The ID of the film media.
     */
    public void setFmId(Long fmId) {
        this.fmId = fmId;
    }

    @Basic
    @Column(name = "fm_film_path")
    private String fmFilmPath;

    /**
     * Gets the file path of the film.
     *
     * @return The file path of the film.
     */
    public String getFmFilmPath() {
        return fmFilmPath;
    }

    /**
     * Sets the file path of the film.
     *
     * @param fmFilmPath The file path of the film.
     */
    public void setFmFilmPath(String fmFilmPath) {
        this.fmFilmPath = fmFilmPath;
    }

    @Basic
    @Column(name = "fm_trailer_path")
    private String fmTrailerPath;

    /**
     * Gets the file path of the film trailer.
     *
     * @return The file path of the film trailer.
     */
    public String getFmTrailerPath() {
        return fmTrailerPath;
    }

    /**
     * Sets the file path of the film trailer.
     *
     * @param fmTrailerPath The file path of the film trailer.
     */
    public void setFmTrailerPath(String fmTrailerPath) {
        this.fmTrailerPath = fmTrailerPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmMediaEntity that = (FilmMediaEntity) o;

        if (!Objects.equals(fmId, that.fmId)) return false;
        if (!Objects.equals(fmFilmPath, that.fmFilmPath)) return false;
        return Objects.equals(fmTrailerPath, that.fmTrailerPath);
    }

    @Override
    public int hashCode() {
        int result = fmId != null ? fmId.hashCode() : 0;
        result = 31 * result + (fmFilmPath != null ? fmFilmPath.hashCode() : 0);
        result = 31 * result + (fmTrailerPath != null ? fmTrailerPath.hashCode() : 0);
        return result;
    }
}
