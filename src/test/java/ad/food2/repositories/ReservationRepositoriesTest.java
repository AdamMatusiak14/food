package ad.food2.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ad.food2.model.Client;
import ad.food2.model.Reservation;
import ad.food2.repository.ReservationRepository;
import ad.food2.service.UsersService;

public class ReservationRepositoriesTest {

    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    UsersService usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindReservationById() {
        Reservation reservation = new Reservation();
        Long id = 1L;
        reservation.setId(id);

        when(reservationRepository.findReservationById(id)).thenReturn(reservation);

        Reservation result = reservationRepository.findReservationById(id);

        assertEquals(id, result.getId());
    }

    @Test
    void testFindByClient() {
        Client client = new Client();
        Reservation reservation = new Reservation();
        reservation.setClient(client);

        when(reservationRepository.findByClient(client)).thenReturn(reservation);

        Reservation result = reservationRepository.findByClient(client);

        assertEquals(result.getClient(), client);

    }
}
