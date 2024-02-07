package ad.food2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ad.food2.model.Local;

@Repository
public interface LocalRepository extends JpaRepository<Local, Long> {

    List<Local> findByReservationId(Long id);

}
