package by.bsuir.mycoolstore.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

/**
 * The FilmEntity class represents a film entry in the database.
 */
@Entity
@Table(name = "film", schema = "mycoolstore")
public class FilmEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "flm_id")
    private Long flmId;

    /**
     * Gets the ID of the film.
     *
     * @return The ID of the film.
     */
    public Long getFlmId() {
        return flmId;
    }

    /**
     * Sets the ID of the film.
     *
     * @param flmId The ID of the film.
     */
    public void setFlmId(Long flmId) {
        this.flmId = flmId;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "m2m_film_category",
            joinColumns = @JoinColumn(name = "fc_film"),
            inverseJoinColumns = @JoinColumn(name = "fc_category")
    )
    private List<CategoryEntity> categories;

    /**
     * Gets the list of categories associated with the film.
     *
     * @return The list of categories.
     */
    public List<CategoryEntity> getCategories() {
        return categories;
    }

    /**
     * Sets the list of categories associated with the film.
     *
     * @param categories The list of categories.
     */
    public void setCategories(List<CategoryEntity> categories) {
        this.categories = categories;
    }

    @Basic
    @Column(name = "flm_description")
    private String flmDescription;

    /**
     * Gets the description of the film.
     *
     * @return The description of the film.
     */
    public String getFlmDescription() {
        return flmDescription;
    }

    /**
     * Sets the description of the film.
     *
     * @param flmDescription The description of the film.
     */
    public void setFlmDescription(String flmDescription) {
        this.flmDescription = flmDescription;
    }

    @Basic
    @Column(name = "flm_price", precision = 5, scale = 2)
    private BigDecimal flmPrice;

    /**
     * Gets the price of the film.
     *
     * @return The price of the film.
     */
    public BigDecimal getFlmPrice() {
        flmPrice = flmPrice.setScale(2, RoundingMode.HALF_UP);
        return flmPrice;
    }

    /**
     * Gets the real price of the film after applying the discount.
     *
     * @return The real price of the film.
     */
    public BigDecimal getRealPrice() {
        return flmPrice.multiply(BigDecimal.valueOf(flmDiscount)).divide(new BigDecimal(100), RoundingMode.HALF_UP);
    }

    /**
     * Sets the price of the film.
     *
     * @param flmPrice The price of the film.
     */
    public void setFlmPrice(BigDecimal flmPrice) {
        this.flmPrice = flmPrice;
    }

    @Basic
    @Column(name = "flm_discount")
    private Short flmDiscount;

    /**
     * Gets the discount percentage of the film.
     *
     * @return The discount percentage of the film.
     */
    public Short getFlmDiscount() {
        return flmDiscount;
    }

    /**
     * Sets the discount percentage of the film.
     *
     * @param flmDiscount The discount percentage of the film.
     */
    public void setFlmDiscount(Short flmDiscount) {
        this.flmDiscount = flmDiscount;
    }

    @Basic
    @Column(name = "flm_author")
    private String flmAuthor;

    /**
     * Gets the author of the film.
     *
     * @return The author of the film.
     */
    public String getFlmAuthor() {
        return flmAuthor;
    }

    /**
     * Sets the author of the film.
     *
     * @param flmAuthor The author of the film.
     */
    public void setFlmAuthor(String flmAuthor) {
        this.flmAuthor = flmAuthor;
    }

    @Basic
    @Column(name = "flm_age")
    private String flmAge;

    /**
     * Gets the age restriction of the film.
     *
     * @return The age restriction of the film.
     */
    public String getFlmAge() {
        return flmAge;
    }

    /**
     * Sets the age restriction of the film.
     *
     * @param flmAge The age restriction of the film.
     */
    public void setFlmAge(String flmAge) {
        this.flmAge = flmAge;
    }

    @Basic
    @Column(name = "flm_name")
    private String flmName;

    /**
     * Gets the name of the film.
     *
     * @return The name of the film.
     */
    public String getFlmName() {
        return flmName;
    }

    /**
     * Sets the name of the film.
     *
     * @param flmName The name of the film.
     */
    public void setFlmName(String flmName) {
        this.flmName = flmName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmEntity that = (FilmEntity) o;

        if (!Objects.equals(flmId, that.flmId)) return false;
        if (!Objects.equals(flmDescription, that.flmDescription)) return false;
        if (!Objects.equals(flmPrice, that.flmPrice)) return false;
        if (!Objects.equals(flmDiscount, that.flmDiscount)) return false;
        if (!Objects.equals(flmAuthor, that.flmAuthor)) return false;
        if (!Objects.equals(flmAge, that.flmAge)) return false;
        return Objects.equals(flmName, that.flmName);
    }

    @Override
    public int hashCode() {
        int result = flmId != null ? flmId.hashCode() : 0;
        result = 31 * result + (flmDescription != null ? flmDescription.hashCode() : 0);
        result = 31 * result + (flmPrice != null ? flmPrice.hashCode() : 0);
        result = 31 * result + (flmDiscount != null ? flmDiscount.hashCode() : 0);
        result = 31 * result + (flmAuthor != null ? flmAuthor.hashCode() : 0);
        result = 31 * result + (flmAge != null ? flmAge.hashCode() : 0);
        result = 31 * result + (flmName != null ? flmName.hashCode() : 0);
        return result;
    }
}
