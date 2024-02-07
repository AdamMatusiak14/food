package ad.food2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ad.food2.model.DaGrasso;
import ad.food2.model.Kfc;
import ad.food2.model.Macdonald;
import ad.food2.object.Basket;
import ad.food2.object.Restaurant;
import ad.food2.service.DishService;

@Controller
public class DishController {

    private DishService dishService;
    private Basket basket;

    public DishController(DishService dishService, Basket basket) {
        this.dishService = dishService;
        this.basket = basket;
    }

    @GetMapping("/show/{restaurantName}")
    public String showMenu(@PathVariable("restaurantName") String name, Model model) {
        switch (name) {

            case "Macdonald":
                List<Macdonald> MacdonaldList = dishService.findMacdonaldList();
                model.addAttribute("ListOfDish", MacdonaldList);
                String Mac = "Macdonald";
                model.addAttribute("Restaurant", Mac);
                return "/showMenu";

            case "Kfc":
                List<Kfc> KfcList = dishService.findKfcList();
                model.addAttribute("ListOfDish", KfcList);
                String Kfc = "Kfc";
                model.addAttribute("Restaurant", Kfc);
                return "/showMenu";

            case "DaGrasso":
                List<DaGrasso> DaGrassoList = dishService.findDaGrassoList();
                model.addAttribute("ListOfDish", DaGrassoList);
                String daGrasso = "DaGrasso";
                model.addAttribute("Restaurant", daGrasso);
                return "/showMenu";
            default:
                System.out.println("Nie pobrano żadnej listy");

        }

        return "showMenu";

    }

    @PostMapping("/addDish")
    public String addDishToBasket(String name, Long id, Model model) {
        Restaurant restaurant = new Restaurant();
        switch (name) {

            case "Macdonald":
                Optional<Macdonald> Macdonald = dishService.findDishMacdonaldById(id);
                if (Macdonald.isPresent()) {
                    Macdonald mac = Macdonald.get();
                    restaurant.setDish(mac.getDish());
                    restaurant.setPrice(mac.getPrice());
                    restaurant.setName(mac.getName());
                    basket.addDish(restaurant);
                }

                return showMenu("Macdonald", model);

            case "Kfc":
                Optional<Kfc> Kfc = dishService.findDishKfcById(id);
                if (Kfc.isPresent()) {
                    Kfc kfc = Kfc.get();
                    restaurant.setDish(kfc.getDish());
                    restaurant.setPrice(kfc.getPrice());
                    restaurant.setName(kfc.getName());
                    basket.addDish(restaurant);
                }
                return showMenu("Kfc", model);

            case "DaGrasso":
                Optional<DaGrasso> DaGrasso = dishService.findDishDaGrassoById(id);
                if (DaGrasso.isPresent()) {
                    DaGrasso dagrasso = DaGrasso.get();
                    restaurant.setDish(dagrasso.getDish());
                    restaurant.setPrice(dagrasso.getPrice());
                    restaurant.setName(dagrasso.getName());
                    basket.addDish(restaurant);
                }
                return showMenu("DaGrasso", model);

        }

        return "showMenu";
    }

}