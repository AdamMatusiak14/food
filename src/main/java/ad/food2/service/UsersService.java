package ad.food2.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ad.food2.Dto.DtoClient;
import ad.food2.Dto.DtoStatus;
import ad.food2.model.Client;
import ad.food2.model.Local;
import ad.food2.model.Reservation;
import ad.food2.object.Status;
import ad.food2.repository.ClientRepository;
import ad.food2.repository.LocalRepository;
import ad.food2.repository.ReservationRepository;

@Service
public class UsersService {

    private ReservationRepository reservationRepository;
    private LocalRepository localRepository;
    private ClientRepository clientRepository;

    public UsersService(ReservationRepository reservationRepository, LocalRepository localRepository,
            ClientRepository clientRepository) {
        this.reservationRepository = reservationRepository;
        this.localRepository = localRepository;
        this.clientRepository = clientRepository;
    }

    public List<Reservation> findAllReservation() {
        List<Reservation> listReservations = reservationRepository.findAll();
        return listReservations;
    }

    public List<Local> findOrderDishes(Long id) {
        List<Local> localList = localRepository.findByReservationId(id);
        return localList;

    }

    public void changeStatus(DtoStatus dtoStatus, Long id) {

        Reservation reservation = reservationRepository.findReservationById(id);
        // reservation.getStatus().
        Status status = dtoStatus.getStatus();
        reservation.setStatus(status);
        reservationRepository.save(reservation);

    }

    public Optional<Client> compareUser(DtoClient dtoClient) {
        Optional<Client> client = clientRepository.findByNameAndPhone(dtoClient.getName(), dtoClient.getPhone());
        return client;
    }

    public BigDecimal findUserAmount(Client client) {
        Reservation reservation = reservationRepository.findByClient(client);
        BigDecimal amount = reservation.getAmount();
        return amount;
    }

    public List<Local> findUserDish(Client client) {
        Reservation reservation = reservationRepository.findByClient(client);
        List<Local> localList = localRepository.findByReservationId(reservation.getId());
        return localList;
    }

    public Status findStatusReservation(Client client) {
        Reservation reservation = reservationRepository.findByClient(client);
        Status status = reservation.getStatus();
        return status;
    }

}
