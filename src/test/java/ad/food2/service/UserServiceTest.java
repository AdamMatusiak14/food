package ad.food2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import ad.food2.Dto.DtoClient;
import ad.food2.Dto.DtoStatus;
import ad.food2.model.Client;
import ad.food2.model.Local;
import ad.food2.model.Reservation;
import ad.food2.object.Status;
import ad.food2.repository.ClientRepository;
import ad.food2.repository.LocalRepository;
import ad.food2.repository.ReservationRepository;

public class UserServiceTest {

    @Mock
    ReservationRepository reservationRepository;

    @Mock
    LocalRepository localRepository;

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    UsersService usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllReservation() {
        List<Reservation> mockReservation = Arrays.asList(new Reservation(), new Reservation());
        when(reservationRepository.findAll()).thenReturn(mockReservation);

        List<Reservation> reservations = usersService.findAllReservation();

        assertEquals(2, reservations.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void testFindOrdersDishes() {
        List<Local> mockLocal = Arrays.asList(new Local(), new Local());
        Long reservationId = 1L;

        when(localRepository.findByReservationId(reservationId)).thenReturn(mockLocal);

        List<Local> localList = usersService.findOrderDishes(reservationId);

        assertEquals(2, localList.size());
        verify(localRepository, times(1)).findByReservationId(reservationId);

    }

    @Test
    void testChangeStatus() {
        DtoStatus dtoStatus = new DtoStatus();
        dtoStatus.setStatus(Status.DELIVERY);
        Reservation reservation = new Reservation();
        Long id = 1L;

        when(reservationRepository.findReservationById(id)).thenReturn(reservation);

        usersService.changeStatus(dtoStatus, id);

        assertEquals(Status.DELIVERY, reservation.getStatus());
        verify(reservationRepository, times(1)).findReservationById(id);
        verify(reservationRepository, times(1)).save(reservation);

    }

    @Test
    void testCompareUser() {
        DtoClient dtoClient = new DtoClient();
        dtoClient.setName("name");
        dtoClient.setPhone("775667888");
        Client client = new Client();
        Optional<Client> clientOptional = Optional.of(client);

        when(clientRepository.findByNameAndPhone(dtoClient.getName(), dtoClient.getPhone())).thenReturn(clientOptional);

        Optional<Client> result = usersService.compareUser(dtoClient);

        assertEquals(clientOptional, result);
        verify(clientRepository, times(1)).findByNameAndPhone(dtoClient.getName(), dtoClient.getPhone());

    }

    @Test
    void testFindUserAmount() {
        Reservation reservation = new Reservation();
        Client client = new Client();

        when(reservationRepository.findByClient(client)).thenReturn(reservation);

        BigDecimal result = usersService.findUserAmount(client);

        assertEquals(reservation.getAmount(), result);
        verify(reservationRepository, times(1)).findByClient(client);

    }

    @Test
    void testFindUserDish() {
        Reservation reservation = new Reservation();
        Client client = new Client();
        List<Local> localList = Arrays.asList(new Local(), new Local());

        when(reservationRepository.findByClient(client)).thenReturn(reservation);

        when(localRepository.findByReservationId(reservation.getId())).thenReturn(localList);

        List<Local> result = usersService.findUserDish(client);

        assertEquals(localList, result);
        verify(reservationRepository, times(1)).findByClient(client);
        verify(localRepository, times(1)).findByReservationId(reservation.getId());
    }

    @Test
    void testFindStatusReservation() {
        Reservation reservation = new Reservation();
        Client client = new Client();
        Status status = Status.DELIVERY;
        reservation.setStatus(status);

        when(reservationRepository.findByClient(client)).thenReturn(reservation);

        Status result = usersService.findStatusReservation(client);

        assertEquals(reservation.getStatus(), result);

        verify(reservationRepository, times(1)).findByClient(client);
    }

}
