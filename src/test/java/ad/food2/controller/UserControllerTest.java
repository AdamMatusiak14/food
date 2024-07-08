package ad.food2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import ad.food2.Dto.DtoClient;
import ad.food2.Dto.DtoStatus;
import ad.food2.model.Client;
import ad.food2.model.Local;
import ad.food2.model.Reservation;
import ad.food2.object.Status;
import ad.food2.service.UsersService;

public class UserControllerTest {

    @Mock
    private UsersService usersService;

    @Mock
    private Model model;

    @InjectMocks
    private UsersController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showReservationTest() {
        List<Reservation> reservationList = Arrays.asList(mock(Reservation.class));

        DtoStatus dtoStatus = new DtoStatus();

        when(usersService.findAllReservation()).thenReturn(reservationList);

        String viewName = userController.showReservation(model);

        assertEquals("/admin", viewName);

        verify(model).addAttribute(eq("dtoStatus"), any(DtoStatus.class));

        verify(model).addAttribute(eq("reservationList"), anyList());
    }

    @Test
    void showOrdersTest() {
        List<Local> localList = Arrays.asList(mock(Local.class));

        when(usersService.findOrderDishes(1L)).thenReturn(localList);

        String viewName = userController.showOrders(1L, model);

        assertEquals("/orders", viewName);

        verify(model).addAttribute(eq("localList"), anyList());
    }

    @Test
    void changeStatusTest() {
        DtoStatus dtoStatus = new DtoStatus();
        Long id = 1L;

        // dtoStatus.setStatus(Status.WAY);

        String viewName = userController.changeStatus(dtoStatus, id);

        verify(usersService, times(1)).changeStatus(dtoStatus, id);

        assertEquals("redirect:admin/reservation", viewName);
    }

    @Test
    void testSetClient() {
        DtoClient dtoClient = new DtoClient();

        String viewName = userController.setClient(model);

        assertEquals("/login", viewName);

        verify(model).addAttribute(eq("dtoClient"), any(DtoClient.class));
    }

    @Test
    void testLoginClientTrue() {
        DtoClient dtoClient = new DtoClient();
        Client client = new Client();
        BigDecimal amount = new BigDecimal(10);
        List<Local> localList = Arrays.asList(mock(Local.class));
        Status status = Status.PREPARATION;

        when(usersService.findUserAmount(client)).thenReturn(amount);
        when(usersService.findUserDish(client)).thenReturn(localList);
        when(usersService.findStatusReservation(client)).thenReturn(status);

        when(usersService.compareUser(dtoClient)).thenReturn(Optional.of(client));

        String viewName = userController.loginClient(dtoClient, model);

        assertEquals("orderClient", viewName);
        verify(model).addAttribute(eq("amount"), any(BigDecimal.class));
        verify(model).addAttribute(eq("status"), any(Status.class));
        verify(model).addAttribute(eq("localList"), anyList());

    }

    @Test
    void testLoginClientFalse() {
        DtoClient dtoClient = new DtoClient();
        Client client = new Client();

        when(usersService.compareUser(dtoClient)).thenReturn(Optional.empty());

        String viewName = userController.loginClient(dtoClient, model);

        assertEquals("badLogin", viewName);

    }

}
