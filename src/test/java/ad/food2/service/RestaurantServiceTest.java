package ad.food2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ad.food2.model.RestaurantInfo;
import ad.food2.repository.RestaurantInfoRepository;

public class RestaurantServiceTest {

    @Mock
    RestaurantInfoRepository restaurantInfoRepository;

    @InjectMocks
    RestaurantService restaurantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowRestaurants() {
        RestaurantInfo restaurantInfo1 = new RestaurantInfo(1L, "name1", new BigDecimal(10), new BigDecimal(20),
                new BigDecimal(25));

        RestaurantInfo restaurantInfo2 = new RestaurantInfo(2L, "name2", new BigDecimal(11), new BigDecimal(21),
                new BigDecimal(24));

        List<RestaurantInfo> restaurants = new ArrayList();
        restaurants.add(restaurantInfo1);
        restaurants.add(restaurantInfo2);

        when(restaurantInfoRepository.findAll()).thenReturn(restaurants);

        List<RestaurantInfo> restInfos = restaurantService.showRestaurant();

        assertEquals(restaurants, restInfos);
        verify(restaurantInfoRepository, times(1)).findAll();

    }

}
