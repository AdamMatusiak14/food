package ad.food2.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import javax.crypto.Mac;

import org.springframework.stereotype.Service;

import ad.food2.model.DaGrasso;
import ad.food2.model.Kfc;
import ad.food2.model.Local;
import ad.food2.model.Macdonald;
import ad.food2.model.Reservation;
import ad.food2.model.RestaurantInfo;
import ad.food2.model.Client;
import ad.food2.object.Basket;
import ad.food2.object.Restaurant;
import ad.food2.object.Status;
import ad.food2.repository.ReservationRepository;
import ad.food2.repository.RestaurantInfoRepository;
import ad.food2.repository.ClientRepository;
import ad.food2.repository.LocalRepository;
import liquibase.pro.packaged.da;

@Service
public class ReservationService {

  private Basket basket; // ListOfDish
  private RestaurantInfoRepository restaurantInfoRepository;
  private ReservationRepository reservationRepository;
  private ClientRepository clientRepository;
  private LocalRepository localRepository;

  public ReservationService(Basket basket, RestaurantInfoRepository restaurantInfoRepository,
      ReservationRepository reservationRepository, ClientRepository clientRepository,
      LocalRepository locaRepository) {
    this.basket = basket;
    this.restaurantInfoRepository = restaurantInfoRepository;
    this.reservationRepository = reservationRepository;
    this.clientRepository = clientRepository;
    this.localRepository = locaRepository;
  }

  public int allDishesCoast(List<Restaurant> restaurants) {
    int priceRest = 0;
    if (restaurants.isEmpty()) {
      return priceRest;
    }
    Iterator<Restaurant> rest = restaurants.iterator();

    String name = restaurants.get(0).getName();

    RestaurantInfo restaurantInfo = restaurantInfoRepository.findByName(name);
    int delivery = restaurantInfo.getDelivery().intValue();
    int minPrice = restaurantInfo.getMinPrice().intValue();

    while (rest.hasNext()) {
      priceRest += rest.next().getPrice().intValue();

    }

    if (priceRest > minPrice) {
      return priceRest;
    } else {
      priceRest = 0;
      return priceRest;
    }

  }

  public int deliveryCoast(List<Restaurant> restaurant) {
    int priceRest = 0;
    if (restaurant.isEmpty()) {
      return priceRest = 0;
    }
    Iterator<Restaurant> rest = restaurant.iterator();

    String name = restaurant.get(0).getName();
    RestaurantInfo restaurantInfo = restaurantInfoRepository.findByName(name);
    int delivery = restaurantInfo.getDelivery().intValue();
    int free = restaurantInfo.getFreeDelivery().intValue();
    int minPrice = restaurantInfo.getMinPrice().intValue();

    while (rest.hasNext()) {
      priceRest += rest.next().getPrice().intValue();

    }

    if (priceRest > minPrice) {
      if (priceRest < free) {
        return delivery;
      } else {
        return priceRest;
      }
    }

    priceRest = 0;
    return priceRest;

  }

  public List<Macdonald> changeListToMacDonalds(List<Restaurant> restaurant) {
    List<Macdonald> mac = new ArrayList<>();
    Macdonald maco = new Macdonald();
    Restaurant rest = new Restaurant();

    Iterator<Restaurant> resIterator = restaurant.iterator();

    while (resIterator.hasNext()) {
      rest = resIterator.next();
      maco.setId(rest.getId());
      maco.setName(rest.getName());
      maco.setDish(rest.getDish());
      maco.setPrice(rest.getPrice());
      mac.add(maco);
      rest = new Restaurant();
      maco = new Macdonald();

    }

    return mac;
  }

  public List<Kfc> changeListToKfc(List<Restaurant> restaurant) {
    List<Kfc> kfc = new ArrayList<>();
    Kfc kfcobj = new Kfc();
    Restaurant rest = new Restaurant();

    Iterator<Restaurant> resIterator = restaurant.iterator();

    while (resIterator.hasNext()) {
      rest = resIterator.next();
      kfcobj.setId(rest.getId());
      kfcobj.setName(rest.getName());
      kfcobj.setDish(rest.getDish());
      kfcobj.setPrice(rest.getPrice());
      kfc.add(kfcobj);
      rest = new Restaurant();
      kfcobj = new Kfc();

    }

    return kfc;
  }

  public List<DaGrasso> changeListToDaGrasso(List<Restaurant> restaurant) {
    List<DaGrasso> daGrassList = new ArrayList<>();
    DaGrasso daGrasso = new DaGrasso();
    Restaurant rest = new Restaurant();

    Iterator<Restaurant> resIterator = restaurant.iterator();

    while (resIterator.hasNext()) {
      rest = resIterator.next();
      daGrasso.setId(rest.getId());
      daGrasso.setName(rest.getName());
      daGrasso.setDish(rest.getDish());
      daGrasso.setPrice(rest.getPrice());
      daGrassList.add(daGrasso);
      rest = new Restaurant();
      daGrasso = new DaGrasso();

    }

    return daGrassList;
  }

  public void saveClient(Client client) {

    clientRepository.save(client);

  }

  public List<Local> changeToLocal(List<Restaurant> restaurands) {
    List<Local> localList = new ArrayList<>();
    Local local = new Local();
    Iterator<Restaurant> restauIterator = restaurands.iterator();

    while (restauIterator.hasNext()) {
      Restaurant rest = restauIterator.next();
      local.setId(rest.getId());
      local.setName(rest.getName());
      local.setDish(rest.getDish());
      local.setPrice(rest.getPrice());
      localList.add(local);
      local = new Local();
      rest = new Restaurant();

    }

    return localList;
  }

  public void saveReservation(Reservation reservation) {
    reservationRepository.save(reservation);

  }

  public void saveLocal(List<Local> LocalList, Reservation reservation) {
    Iterator<Local> localIterator = LocalList.iterator();
    Local local = new Local();

    while (localIterator.hasNext()) {
      local = localIterator.next();
      String name = local.getName();
      String dish = local.getDish();
      BigDecimal price = local.getPrice();
      // local.setReservation(reservation);
      local = new Local(name, dish, price, reservation);
      localRepository.save(local);
      local = new Local();

    }
  }

}
