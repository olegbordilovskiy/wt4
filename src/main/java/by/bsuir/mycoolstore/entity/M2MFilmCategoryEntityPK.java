package by.bsuir.mycoolstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Objects;

/**
 * The M2MFilmCategoryEntityPK class represents the primary key for the M2MFilmCategoryEntity class.
 * It consists of the film ID and category ID.
 */
public class M2MFilmCategoryEntityPK implements Serializable {
    @Column(name = "fc_film")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fcFilm;

    /**
     * Gets the film ID.
     *
     * @return The film ID.
     */
    public Long getFcFilm() {
        return fcFilm;
    }

    /**
     * Sets the film ID.
     *
     * @param fcFilm The film ID.
     */
    public void setFcFilm(Long fcFilm) {
        this.fcFilm = fcFilm;
    }

    @Column(name = "fc_category")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fcCategory;

    /**
     * Gets the category ID.
     *
     * @return The category ID.
     */
    public Long getFcCategory() {
        return fcCategory;
    }

    /**
     * Sets the category ID.
     *
     * @param fcCategory The category ID.
     */
    public void setFcCategory(Long fcCategory) {
        this.fcCategory = fcCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        M2MFilmCategoryEntityPK that = (M2MFilmCategoryEntityPK) o;

        if (!Objects.equals(fcFilm, that.fcFilm)) return false;
        return Objects.equals(fcCategory, that.fcCategory);
    }

    @Override
    public int hashCode() {
        int result = fcFilm != null ? fcFilm.hashCode() : 0;
        result = 31 * result + (fcCategory != null ? fcCategory.hashCode() : 0);
        return result;
    }
}
