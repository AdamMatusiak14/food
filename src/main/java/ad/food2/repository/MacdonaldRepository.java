package ad.food2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import ad.food2.model.Macdonald;

@Repository
public interface MacdonaldRepository extends JpaRepository<Macdonald, Long> {

}
