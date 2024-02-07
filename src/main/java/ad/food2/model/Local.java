package ad.food2.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Local")
public class Local {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String dish;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    public Local() {
    }

    public Local(Long id, String name, String dish, BigDecimal price, Reservation reservation) {
        this.id = id;
        this.name = name;
        this.dish = dish;
        this.price = price;
        this.reservation = reservation;
    }

    public Local(String name, String dish, BigDecimal price, Reservation reservation) {

        this.name = name;
        this.dish = dish;
        this.price = price;
        this.reservation = reservation;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Reservation getReservation() {
        return this.reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

}
