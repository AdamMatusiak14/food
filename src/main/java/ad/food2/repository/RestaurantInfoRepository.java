package ad.food2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ad.food2.model.RestaurantInfo;
import java.util.List;

@Repository
public interface RestaurantInfoRepository extends JpaRepository<RestaurantInfo, Long> {

    RestaurantInfo findByName(String name);
}
