package ad.food2.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
// @Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String phone;
    @OneToMany(mappedBy = "client")
    private List<Reservation> ListOfOrder = new ArrayList<>();

    public Client() {
    }

    public Client(Long id, String name, String address, String phone) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Client(Long id, String name, String address, String phone, List<Reservation> ListOfOrder) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.ListOfOrder = ListOfOrder;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Reservation> getListOfOrder() {
        return this.ListOfOrder;
    }

    public void setListOfOrder(List<Reservation> ListOfOrder) {
        this.ListOfOrder = ListOfOrder;
    }

}
