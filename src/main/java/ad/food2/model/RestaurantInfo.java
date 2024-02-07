package ad.food2.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "restaurant_info")
public class RestaurantInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "min_price")
    private BigDecimal minPrice;
    @Column(name = "free_delivery")
    private BigDecimal freeDelivery;
    private BigDecimal delivery;

    public RestaurantInfo() {
    }

    public RestaurantInfo(Long id, String name, BigDecimal minPrice, BigDecimal freeDelivery, BigDecimal delivery) {
        this.id = id;
        this.name = name;
        this.minPrice = minPrice;
        this.freeDelivery = freeDelivery;
        this.delivery = delivery;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getMinPrice() {
        return this.minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getFreeDelivery() {
        return this.freeDelivery;
    }

    public void setFreeDelivery(BigDecimal freeDelivery) {
        this.freeDelivery = freeDelivery;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDelivery() {
        return this.delivery;
    }

    public void setDelivery(BigDecimal delivery) {
        this.delivery = delivery;
    }

}
