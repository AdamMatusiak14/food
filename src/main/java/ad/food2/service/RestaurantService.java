package ad.food2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ad.food2.model.RestaurantInfo;
import ad.food2.repository.RestaurantInfoRepository;

@Service
public class RestaurantService {
    private RestaurantInfoRepository restaurantInfoRepository;

    RestaurantService(RestaurantInfoRepository restaurantInfoRepository) {
        this.restaurantInfoRepository = restaurantInfoRepository;
    }

    public List<RestaurantInfo> showRestaurant() {
        List<RestaurantInfo> restaurands = new ArrayList<>();
        restaurands = restaurantInfoRepository.findAll();
        return restaurands;
    }
}
