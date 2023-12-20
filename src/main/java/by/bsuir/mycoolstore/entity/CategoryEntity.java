package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

/**
 * The CategoryEntity class represents a category in the database.
 */
@Entity
@Table(name = "category", schema = "mycoolstore")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cat_id")
    private Long catId;

    /**
     * Gets the ID of the category.
     *
     * @return The ID of the category.
     */
    public Long getCatId() {
        return catId;
    }

    /**
     * Sets the ID of the category.
     *
     * @param catId The ID of the category.
     */
    public void setCatId(Long catId) {
        this.catId = catId;
    }

    @ManyToMany(mappedBy = "categories")
    private List<FilmEntity> films;

    /**
     * Gets the list of films associated with the category.
     *
     * @return The list of films.
     */
    public List<FilmEntity> getFilms() {
        return films;
    }

    /**
     * Sets the list of films associated with the category.
     *
     * @param films The list of films.
     */
    public void setFilms(List<FilmEntity> films) {
        this.films = films;
    }

    @Basic
    @Column(name = "cat_name")
    private String catName;

    /**
     * Gets the name of the category.
     *
     * @return The name of the category.
     */
    public String getCatName() {
        return catName;
    }

    /**
     * Sets the name of the category.
     *
     * @param catName The name of the category.
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (!Objects.equals(catId, that.catId)) return false;
        return Objects.equals(catName, that.catName);
    }

    @Override
    public int hashCode() {
        int result = catId != null ? catId.hashCode() : 0;
        result = 31 * result + (catName != null ? catName.hashCode() : 0);
        return result;
    }
}
