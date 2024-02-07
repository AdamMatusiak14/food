package ad.food2.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import ad.food2.Dto.DtoCoast;
import ad.food2.Dto.DtoStatus;
import ad.food2.model.DaGrasso;
import ad.food2.model.Kfc;
import ad.food2.model.Local;
import ad.food2.model.Macdonald;
import ad.food2.model.Reservation;
import ad.food2.model.Client;
import ad.food2.object.Basket;
import ad.food2.object.Restaurant;
import ad.food2.object.Status;
import ad.food2.service.ReservationService;

@Controller
public class ReservationController {

    private Basket basket; // ListOfDish
    private ReservationService reservationService;

    public ReservationController(Basket basket, ReservationService reservationService) {
        this.basket = basket;
        this.reservationService = reservationService;
    }

    @GetMapping("/order/basket")
    public String showBasket(Model model) {
        DtoCoast dtoCoast = new DtoCoast();
        model.addAttribute("dtoCoast", dtoCoast);
        return "/basket";
    }

    @GetMapping("order/remove/{DishId}")
    public String removeDishFromOrder(@PathVariable("DishId") Long id, Model model) {
        DtoCoast dtoCoast = new DtoCoast();
        model.addAttribute("dtoCoast", dtoCoast);
        basket.removeDish(id);
        return "/basket";
    }

    @GetMapping("/order/amount") // twoje zamowienie
    public String countAmount(Model model) {
        List<Restaurant> macdonald = basket.findAllByName("Macdonald");
        List<Restaurant> kfc = basket.findAllByName("Kfc");
        List<Restaurant> daGrasso = basket.findAllByName("DaGrasso");
        // int macRest = orderService.amountRest(macdonald);
        // int kfcRest = orderService.amountRest(kfc);
        // int daGrassoRest = orderService.amountRes(daGrasso);

        int allDishesCoast = reservationService.allDishesCoast(macdonald) + reservationService.allDishesCoast(kfc)
                + reservationService.allDishesCoast(daGrasso);

        int deliveryCoast = reservationService.deliveryCoast(macdonald) + reservationService.deliveryCoast(kfc)
                + reservationService.deliveryCoast(daGrasso);
        int allCoast = allDishesCoast + deliveryCoast;

        boolean flag = true;

        DtoCoast dtoCoast = new DtoCoast(allDishesCoast, deliveryCoast, allCoast, flag);

        model.addAttribute("dtoCoast", dtoCoast);

        return "/basket";

    }

    @GetMapping("order/client")
    public String setUser(Model model) {
        Client client = new Client();
        model.addAttribute("client", client);
        return "userInfo";

    }

    @PostMapping("order/saveOrder")
    public String saveOrder(Client client) {

        // Zapis usera do bazy
        reservationService.saveClient(client);
        List<Restaurant> macdonald = basket.findAllByName("Macdonald");
        List<Restaurant> kfc = basket.findAllByName("Kfc");
        List<Restaurant> daGrasso = basket.findAllByName("DaGrasso");

        int allDishesCoast = reservationService.allDishesCoast(macdonald) + reservationService.allDishesCoast(kfc)
                + reservationService.allDishesCoast(daGrasso);

        int deliveryCoast = reservationService.deliveryCoast(macdonald) + reservationService.deliveryCoast(kfc)
                + reservationService.deliveryCoast(daGrasso);
        int allCoast = allDishesCoast + deliveryCoast;

        // zamiana z powrotem na listę Macdonald

        // List<Macdonald> macList = orderService.changeListToMacDonalds(macdonald);

        // List<Kfc> kfcList = orderService.changeListToKfc(kfc);

        // List<DaGrasso> daGrassList = orderService.changeListToDaGrasso(daGrasso);

        BigDecimal coast = BigDecimal.valueOf(allCoast); // amount

        Status status = Status.PREPARATION;
        System.out.println(status);

        List<Restaurant> restaurants = basket.getListOfDish();
        List<Local> LocalList = reservationService.changeToLocal(restaurants);

        Reservation reservation = new Reservation(client, coast, status, LocalList);
        reservationService.saveReservation(reservation);
        reservationService.saveLocal(LocalList, reservation);

        return "/confirmReservation";

    }

    @PostMapping("/clearBasket")
    String clearBasket() {
        basket.cleanSum();
        return "/index";

    }

}
