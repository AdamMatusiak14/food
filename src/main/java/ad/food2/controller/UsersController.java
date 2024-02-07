package ad.food2.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import ad.food2.Dto.DtoClient;
import ad.food2.Dto.DtoStatus;
import ad.food2.model.Client;
import ad.food2.model.Local;
import ad.food2.model.Reservation;
import ad.food2.object.Status;
import ad.food2.service.UsersService;

@Controller
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {

        this.usersService = usersService;

    }

    @GetMapping("admin/reservation")
    public String showReservation(Model model) {
        List<Reservation> reservationList = usersService.findAllReservation();
        DtoStatus dtoStatus = new DtoStatus();
        model.addAttribute("reservationList", reservationList);
        model.addAttribute("dtoStatus", dtoStatus);
        return "/admin";
    }

    @PostMapping("/orders")
    public String showOrders(Long id, Model model) {
        List<Local> localList = usersService.findOrderDishes(id);
        model.addAttribute("localList", localList);
        return "/orders";
    }

    @PutMapping("/change")
    public String changeStatus(DtoStatus dtoStatus, Long id) {
        usersService.changeStatus(dtoStatus, id);
        return "redirect:admin/reservation";
    }

    @GetMapping("/login")
    public String setClient(Model model) {
        DtoClient dtoClient = new DtoClient();
        model.addAttribute("dtoClient", dtoClient);
        return "/login";
    }

    @PostMapping("/login")
    public String loginClient(DtoClient dtoClient, Model model) {
        Optional<Client> client = usersService.compareUser(dtoClient);
        if (client.isPresent()) {
            Client cli = client.get();
            BigDecimal amount = usersService.findUserAmount(cli);
            List<Local> localList = usersService.findUserDish(cli);
            Status status = usersService.findStatusReservation(cli);
            model.addAttribute("amount", amount);
            model.addAttribute("status", status);
            model.addAttribute("localList", localList);
            return "orderClient";
        }
        return "badLogin";

    }

    // public BigDecimal findUserAmount(Long id){
    // BigDecimal amount;
    // usersService.findUserAmount(id);
    // return amount;
    // }

}
