package ad.food2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ad.food2.model.Kfc;

@Repository
public interface KfcRepository extends JpaRepository<Kfc, Long> {

}
