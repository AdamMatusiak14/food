package ad.food2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ad.food2.model.Client;
import ad.food2.model.DaGrasso;
import ad.food2.model.Kfc;
import ad.food2.model.Local;
import ad.food2.model.Macdonald;
import ad.food2.model.Reservation;
import ad.food2.model.RestaurantInfo;
import ad.food2.object.Basket;
import ad.food2.object.Restaurant;
import ad.food2.repository.ClientRepository;
import ad.food2.repository.LocalRepository;
import ad.food2.repository.ReservationRepository;
import ad.food2.repository.RestaurantInfoRepository;

public class ReservationServiceTest {

    @Mock
    Basket basket;

    @Mock
    RestaurantInfoRepository restaurantInfoRepository;

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    ClientRepository clientRepository;

    @Mock
    LocalRepository localRepository;

    @InjectMocks
    ReservationService reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAllDishesCoast_EmptyRestaurants() {
        List<Restaurant> restaurants = Collections.emptyList();
        int result = reservationService.allDishesCoast(restaurants);

        assertEquals(0, result);

    }

    @Test
    void testAllDishesCoast_AboveMinPrice() {
        List<Restaurant> restaurants = Arrays.asList(
                new Restaurant("Rest1", BigDecimal.valueOf(30)),
                new Restaurant("Rest1", BigDecimal.valueOf(40)));

        RestaurantInfo restaurantInfo = new RestaurantInfo();
        restaurantInfo.setMinPrice(BigDecimal.valueOf(50));
        restaurantInfo.setDelivery(BigDecimal.valueOf(10));

        when(restaurantInfoRepository.findByName("Rest1")).thenReturn(restaurantInfo);

        int result = reservationService.allDishesCoast(restaurants);

        assertEquals(70, result);

    }

    @Test
    void testAllDishesCoast_BelowMinPrice() {
        List<Restaurant> restaurants = Arrays.asList(
                new Restaurant("Rest1", BigDecimal.valueOf(15)),
                new Restaurant("Rest1", BigDecimal.valueOf(20)));

        RestaurantInfo restaurantInfo = new RestaurantInfo();
        restaurantInfo.setMinPrice(BigDecimal.valueOf(50));
        restaurantInfo.setDelivery(BigDecimal.valueOf(10));

        when(restaurantInfoRepository.findByName("Rest1")).thenReturn(restaurantInfo);

        int result = reservationService.allDishesCoast(restaurants);

        assertEquals(0, result);

    }

    @Test
    void testDeliveryCoast_EmptyRestaurants() {
        List<Restaurant> restaurants = Collections.emptyList();
        int result = reservationService.deliveryCoast(restaurants);
        assertEquals(0, result);

    }

    @Test
    void testDeliveryCoast_AboveMinPrice_AboveFree() {
        List<Restaurant> restaurants = Arrays.asList(
                new Restaurant("Rest1", BigDecimal.valueOf(30)),
                new Restaurant("Rest2", BigDecimal.valueOf(40)));

        RestaurantInfo restaurantInfo = new RestaurantInfo();
        restaurantInfo.setDelivery(BigDecimal.valueOf(20));
        restaurantInfo.setFreeDelivery(BigDecimal.valueOf(50));
        // 70>50
        restaurantInfo.setMinPrice(BigDecimal.valueOf(20));
        // 70>20

        when(restaurantInfoRepository.findByName("Rest1")).thenReturn(restaurantInfo);

        int result = reservationService.deliveryCoast(restaurants);
        assertEquals(70, result);
    }

    @Test
    void testDeliveryCoast_AboveMinPrice_BelowFree() {
        List<Restaurant> restaurants = Arrays.asList(
                new Restaurant("Rest1", BigDecimal.valueOf(30)),
                new Restaurant("Rest2", BigDecimal.valueOf(40)));

        RestaurantInfo restaurantInfo = new RestaurantInfo();
        restaurantInfo.setDelivery(BigDecimal.valueOf(25));
        restaurantInfo.setFreeDelivery(BigDecimal.valueOf(100));
        // 70<100
        restaurantInfo.setMinPrice(BigDecimal.valueOf(20));
        // 70>20

        when(restaurantInfoRepository.findByName("Rest1")).thenReturn(restaurantInfo);

        int result = reservationService.deliveryCoast(restaurants);
        assertEquals(25, result);
    }

    @Test
    void testDeliveryCoast_BelowMinPrice() {
        List<Restaurant> restaurants = Arrays.asList(
                new Restaurant("Rest1", BigDecimal.valueOf(10)),
                new Restaurant("Rest2", BigDecimal.valueOf(20)));

        RestaurantInfo restaurantInfo = new RestaurantInfo();
        restaurantInfo.setDelivery(BigDecimal.valueOf(25));
        restaurantInfo.setFreeDelivery(BigDecimal.valueOf(100));
        restaurantInfo.setMinPrice(BigDecimal.valueOf(50));
        // zamowienie 30<50 minPrice

        when(restaurantInfoRepository.findByName("Rest1")).thenReturn(restaurantInfo);

        int result = reservationService.deliveryCoast(restaurants);
        assertEquals(0, result);
    }

    @Test
    void testChangeListToMacDonalds() {
        Restaurant restaurant1 = new Restaurant(1L, "Macdonald", "BigMac", new BigDecimal(20.0));
        Restaurant restaurant2 = new Restaurant(2L, "Macdonald", "fries", new BigDecimal(10.0));

        List<Restaurant> restaurantList = new ArrayList();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);

        List<Macdonald> macdonaldList = reservationService.changeListToMacDonalds(restaurantList);

        assertEquals(2, macdonaldList.size());

        Macdonald daGrass1 = macdonaldList.get(0);
        assertEquals(1L, daGrass1.getId());
        assertEquals("Macdonald", daGrass1.getName());
        assertEquals("BigMac", daGrass1.getDish());
        assertEquals(new BigDecimal(20), daGrass1.getPrice());

        Macdonald daGrass2 = macdonaldList.get(1);
        assertEquals(2L, daGrass2.getId());
        assertEquals("Macdonald", daGrass2.getName());
        assertEquals("fries", daGrass2.getDish());
        assertEquals(new BigDecimal(10), daGrass2.getPrice());

    }

    @Test
    void testChangeListToKfc() {
        Restaurant restaurant1 = new Restaurant(1L, "Kfc", "chicken", new BigDecimal(20.0));
        Restaurant restaurant2 = new Restaurant(2L, "Kfc", "grander", new BigDecimal(10.0));

        List<Restaurant> restaurantList = new ArrayList();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);

        List<Kfc> kfcList = reservationService.changeListToKfc(restaurantList);

        assertEquals(2, kfcList.size());

        Kfc daGrass1 = kfcList.get(0);
        assertEquals(1L, daGrass1.getId());
        assertEquals("Kfc", daGrass1.getName());
        assertEquals("chicken", daGrass1.getDish());
        assertEquals(new BigDecimal(20), daGrass1.getPrice());

        Kfc daGrass2 = kfcList.get(1);
        assertEquals(2L, daGrass2.getId());
        assertEquals("Kfc", daGrass2.getName());
        assertEquals("grander", daGrass2.getDish());
        assertEquals(new BigDecimal(10), daGrass2.getPrice());

    }

    @Test
    void testChangeListToKDaGrasso() {
        Restaurant restaurant1 = new Restaurant(1L, "DaGrasso", "margherita", new BigDecimal(20.0));
        Restaurant restaurant2 = new Restaurant(2L, "DaGrasso", "capricciosa", new BigDecimal(10.0));

        List<Restaurant> restaurantList = new ArrayList();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);

        List<DaGrasso> daGrassoList = reservationService.changeListToDaGrasso(restaurantList);

        assertEquals(2, daGrassoList.size());

        DaGrasso daGrass1 = daGrassoList.get(0);
        assertEquals(1L, daGrass1.getId());
        assertEquals("Kfc", daGrass1.getName());
        assertEquals("chicken", daGrass1.getDish());
        assertEquals(new BigDecimal(20), daGrass1.getPrice());

        DaGrasso daGrass2 = daGrassoList.get(1);
        assertEquals(2L, daGrass2.getId());
        assertEquals("Kfc", daGrass2.getName());
        assertEquals("grander", daGrass2.getDish());
        assertEquals(new BigDecimal(10), daGrass2.getPrice());

    }

    @Test
    void testSaveClient() {
        Client client = new Client();
        client.setId(1L);
        client.setName("John Doe");

        clientRepository.save(client);

        verify(clientRepository, times(1)).save(client);

    }

    @Test
    void testChangeLocal() {

        Restaurant restaurant1 = new Restaurant(1L, "name1", "dish1", new BigDecimal(20.0));
        Restaurant restaurant2 = new Restaurant(2L, "name2", "dish2", new BigDecimal(10.0));

        List<Restaurant> restaurantList = new ArrayList();
        restaurantList.add(restaurant1);
        restaurantList.add(restaurant2);

        List<Local> LocalList = reservationService.changeToLocal(restaurantList);

        assertEquals(2, LocalList.size());

        Local local1 = LocalList.get(0);
        assertEquals(1L, local1.getId());
        assertEquals("name1", local1.getName());
        assertEquals("dish1", local1.getDish());
        assertEquals(new BigDecimal(20), local1.getPrice());

        Local local2 = LocalList.get(1);
        assertEquals(2L, local2.getId());
        assertEquals("name2", local2.getName());
        assertEquals("dish2", local2.getDish());
        assertEquals(new BigDecimal(10), local2.getPrice());

    }

    @Test
    void testSaveReservation() {
        Reservation reservation = new Reservation();
        reservation.setId(1L);

        reservationRepository.save(reservation);

        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testSaveLocal() {
        Reservation reservation = new Reservation();
        Local local1 = new Local(1L, "name1", "dish1", new BigDecimal(20), reservation);
        Local local2 = new Local(2L, "name2", "dish2", new BigDecimal(30), reservation);

        List<Local> localList = new ArrayList();
        localList.add(local1);
        localList.add(local2);

        reservationService.saveLocal(localList, reservation);

        for (Local local : localList) {
            verify(localRepository, times(1)).save(argThat(saveLocal -> saveLocal.getName().equals(local.getName()) &&
                    saveLocal.getDish().equals(local.getDish()) &&
                    saveLocal.getPrice().equals(local.getPrice()) &&
                    saveLocal.getReservation().equals(local.getReservation())));
        }

    }
}
