package ad.food2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ad.food2.model.DaGrasso;

@Repository
public interface DaGrassoRepository extends JpaRepository<DaGrasso, Long> {

}
