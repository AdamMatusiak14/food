package ad.food2.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ad.food2.model.RestaurantInfo;
import ad.food2.service.RestaurantService;

@Controller
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("/restaurant")
    public String showRestaurant(Model model) {
        List<RestaurantInfo> restaurants = restaurantService.showRestaurant();
        model.addAttribute("restaurants", restaurants);
        return "/restaurant";
    }

}
