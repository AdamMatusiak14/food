package ad.food2.object;

import java.math.BigDecimal;

public class Restaurant {

    private Long id;
    private String name;
    private String dish;
    private BigDecimal price;

    public Restaurant() {
    }

    public Restaurant(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public Restaurant(Long id, String name, String dish, BigDecimal price) {
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
