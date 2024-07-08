package ad.food2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyListOf;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import ad.food2.model.RestaurantInfo;
import ad.food2.service.RestaurantService;

public class RestaurantControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @Mock
    private Model model;

    @InjectMocks
    private RestaurantController restaurantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void showRestaurantTest() {

        List<RestaurantInfo> restaurantInfo = Arrays.asList(mock(RestaurantInfo.class));

        String viewName = restaurantController.showRestaurant(model);

        when(restaurantService.showRestaurant()).thenReturn(restaurantInfo);

        assertEquals("/restaurant", viewName);

        verify(model).addAttribute(eq("restaurants"), anyList());
    }

}
