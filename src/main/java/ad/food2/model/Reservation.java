package ad.food2.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import ad.food2.object.Status;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private BigDecimal amount;
    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @OneToMany(mappedBy = "reservation")
    private List<Local> dishes = new ArrayList<>();

    public Reservation() {
    }

    public Reservation(Long id, Client client, BigDecimal amount, Status status, List<Local> dishes) {
        this.id = id;
        this.client = client;
        this.amount = amount;
        this.status = status;
        this.dishes = dishes;
    }

    public Reservation(Client client, BigDecimal amount, Status status, List<Local> dishes) {
        this.client = client;
        this.amount = amount;
        this.status = status;
        this.dishes = dishes;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Client getClient() {
        return this.client;
    }

    public void setUser(Client client) {
        this.client = client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

}
