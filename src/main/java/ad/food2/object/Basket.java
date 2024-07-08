package ad.food2.object;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Basket {

    private List<Restaurant> ListOfDish = new ArrayList<>();
    private BigDecimal sum = BigDecimal.ZERO;

    public void addDish(Restaurant restaurant) {
        Long nr = (long) ListOfDish.size() + 1;
        restaurant.setId(nr);
        ListOfDish.add(restaurant);
        recalculatePrice();

    }

    private void recalculatePrice() {
        BigDecimal price = BigDecimal.ZERO;

        for (Restaurant re : ListOfDish) {
            price = price.add(re.getPrice());
        }
        this.sum = price;

    }

    public void removeDish(Long id) {
        for (Restaurant dish : ListOfDish) {
            if (dish.getId().equals(id)) {
                ListOfDish.remove(dish);
                recalculatePrice();
                break;
            }
        }
    }

    public List<Restaurant> findAllByName(String name) {

        List<Restaurant> restList = new ArrayList<>();
        Iterator<Restaurant> listRestaurant = ListOfDish.iterator();
        Restaurant rest = new Restaurant();

        while (listRestaurant.hasNext()) {
            rest = listRestaurant.next();
            if ((rest.getName()).equals(name)) {
                restList.add(rest);
            }
        }
        return restList;
    }

    public void cleanSum() {
        sum = BigDecimal.ZERO;
        ListOfDish.clear(); // Tu skończyłeś, musisz przejśc ścieżkę po koleji, żeby zobaczyć co się dzieje

    }

    public List<Restaurant> getListOfDish() {
        return this.ListOfDish;
    }

    public void setListOfDish(List<Restaurant> ListOfDish) {
        this.ListOfDish = ListOfDish;
    }

    public BigDecimal getSum() {
        return this.sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

}
