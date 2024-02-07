package ad.food2.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Kfc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dish;
    private BigDecimal price;
    private String name;

    public Kfc() {
    }

    public Kfc(Long id, String name, String dish, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.dish = dish;
        this.price = price;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDish() {
        return this.dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
