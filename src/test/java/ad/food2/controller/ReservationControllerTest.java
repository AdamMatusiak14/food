package ad.food2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import ad.food2.Dto.DtoCoast;
import ad.food2.model.Client;
import ad.food2.model.Local;
import ad.food2.model.Reservation;
import ad.food2.object.Basket;
import ad.food2.object.Restaurant;
import ad.food2.service.ReservationService;

public class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @Mock
    private Model model;

    @Mock
    private Basket basket;

    @Mock
    private Restaurant restaurant;

    @Mock
    private Local local;

    @InjectMocks
    private ReservationController reservationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showBasketTest() {
        DtoCoast dtoCoast = new DtoCoast();

        String viewName = reservationController.showBasket(model);

        verify(model, times(1)).addAttribute(eq("dtoCoast"), any(DtoCoast.class));

        assertEquals("/basket", viewName);
    }

    @Test
    void removeDishFromOrderTest() {
        DtoCoast dtoCoast = new DtoCoast();

        String viewName = reservationController.removeDishFromOrder(1L, model);

        verify(model, times(1)).addAttribute(eq("dtoCoast"), any(DtoCoast.class));

        verify(basket, times(1)).removeDish(1L);

        assertEquals("/basket", viewName);
    }

    @Test
    void coastAmountTest() {
        List<Restaurant> macdonald = new ArrayList<>();
        List<Restaurant> kfc = new ArrayList<>();
        List<Restaurant> daGrasso = new ArrayList<>();

        when(basket.findAllByName("Macdonald")).thenReturn(macdonald);
        when(basket.findAllByName("Kfc")).thenReturn(kfc);
        when(basket.findAllByName("DaGrasso")).thenReturn(daGrasso);

        when(reservationService.allDishesCoast(macdonald)).thenReturn(10);
        when(reservationService.allDishesCoast(kfc)).thenReturn(12);
        when(reservationService.allDishesCoast(daGrasso)).thenReturn(14);

        when(reservationService.deliveryCoast(macdonald)).thenReturn(5);
        when(reservationService.deliveryCoast(kfc)).thenReturn(6);
        when(reservationService.deliveryCoast(daGrasso)).thenReturn(7);

        String viewName = reservationController.countAmount(model);

        assertEquals("/basket", viewName);
        verify(model).addAttribute(eq("dtoCoast"), any(DtoCoast.class));

        verify(reservationService, times(3)).allDishesCoast(anyList());

        verify(reservationService, times(3)).deliveryCoast(anyList());

    }

    @Test
    void setUserTest() {

        Client client = new Client();

        String viewName = reservationController.setUser(model);

        assertEquals("userInfo", viewName);
        verify(model).addAttribute(eq("client"), any(Client.class));

    }

    @Test
    void saveOrderTest() {
        Client client = new Client();
        List<Restaurant> macdonald = new ArrayList<>();
        List<Restaurant> kfc = new ArrayList<>();
        List<Restaurant> daGrasso = new ArrayList<>();
        List<Restaurant> restaurants = Arrays.asList(mock(Restaurant.class));
        List<Local> locals = Arrays.asList(mock(Local.class));

        when(basket.findAllByName("Macdonald")).thenReturn(macdonald);
        when(basket.findAllByName("Kfc")).thenReturn(kfc);
        when(basket.findAllByName("DaGrasso")).thenReturn(daGrasso);

        when(reservationService.allDishesCoast(macdonald)).thenReturn(10);
        when(reservationService.allDishesCoast(kfc)).thenReturn(12);
        when(reservationService.allDishesCoast(daGrasso)).thenReturn(anyInt());

        when(reservationService.deliveryCoast(macdonald)).thenReturn(5);
        when(reservationService.deliveryCoast(kfc)).thenReturn(6);
        when(reservationService.deliveryCoast(daGrasso)).thenReturn(7);

        when(basket.getListOfDish()).thenReturn(restaurants);
        when(reservationService.changeToLocal(restaurants)).thenReturn(locals);

        String viewName = reservationController.saveOrder(client);

        assertEquals("/confirmReservation", viewName);

        verify(reservationService).saveClient(client);

        verify(reservationService).saveReservation(any(Reservation.class));
        verify(reservationService).saveLocal(eq(locals), any(Reservation.class));
        verify(reservationService, times(3)).allDishesCoast(anyList());
        verify(reservationService, times(3)).deliveryCoast(anyList());

    }

    @Test
    void clearBasketTest() {
        String viewName = reservationController.clearBasket();

        assertEquals("/index", viewName);
        verify(basket).cleanSum();
    }
}
