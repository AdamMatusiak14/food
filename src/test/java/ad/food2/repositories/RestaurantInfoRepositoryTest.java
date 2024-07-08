package ad.food2.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ad.food2.model.RestaurantInfo;
import ad.food2.repository.RestaurantInfoRepository;
import ad.food2.service.UsersService;

public class RestaurantInfoRepositoryTest {

    @Mock
    RestaurantInfoRepository restaurantInfoRepository;

    @InjectMocks
    UsersService usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByName() {
        RestaurantInfo restaurantInfo = new RestaurantInfo();
        String name = "JoeDoe";
        restaurantInfo.setName(name);

        when(restaurantInfoRepository.findByName(name)).thenReturn(restaurantInfo);

        RestaurantInfo result = restaurantInfoRepository.findByName(name);

        assertEquals(restaurantInfo, result);

    }
}
