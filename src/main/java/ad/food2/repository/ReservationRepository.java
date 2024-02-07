package ad.food2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ad.food2.model.Client;
import ad.food2.model.Reservation;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findReservationById(Long id);

    Reservation findByClient(Client client);
}
