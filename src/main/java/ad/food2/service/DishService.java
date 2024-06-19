package ad.food2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import ad.food2.model.DaGrasso;
import ad.food2.model.Kfc;
import ad.food2.model.Macdonald;
import ad.food2.object.Restaurant;
import ad.food2.repository.DaGrassoRepository;
import ad.food2.repository.KfcRepository;
import ad.food2.repository.MacdonaldRepository;

@Service
public class DishService {

    private MacdonaldRepository macdonaldRepository;
    private KfcRepository kfcRepository;
    private DaGrassoRepository daGrassoRepository;

    public DishService(MacdonaldRepository macdonaldRepository, KfcRepository kfcRepository,
            DaGrassoRepository daGrassoRepository) {
        this.macdonaldRepository = macdonaldRepository;
        this.kfcRepository = kfcRepository;
        this.daGrassoRepository = daGrassoRepository;
    }

    public List<Macdonald> findMacdonaldList() {
        return macdonaldRepository.findAll();
    }

    public List<Kfc> findKfcList() {
        return kfcRepository.findAll();
    }

    public List<DaGrasso> findDaGrassoList() {
        return daGrassoRepository.findAll();
    }

    public Optional<Macdonald> findDishMacdonaldById(Long id) {
        return macdonaldRepository.findById(id);
    }

    public Optional<Kfc> findDishKfcById(Long id) {
        return kfcRepository.findById(id);
    }

    public Optional<DaGrasso> findDishDaGrassoById(Long id) {
        return daGrassoRepository.findById(id);
    }

    public String showMenu(String name, Model model) {
        switch (name) {
            case "Macdonald":
                List<Macdonald> MacdonaldList = macdonaldRepository.findAll();
                model.addAttribute("ListOfDish", MacdonaldList);
                String Mac = "Macdonald";
                model.addAttribute("Restaurant", Mac);
                return "/showMenu";
            case "Kfc":
                List<Kfc> KfcList = kfcRepository.findAll();
                model.addAttribute("ListOfDish", KfcList);
                String Kfc = "Kfc";
                model.addAttribute("Restaurant", Kfc);
                return "/showMenu";
            case "DaGrasso":
                List<DaGrasso> DaGrassoList = daGrassoRepository.findAll();
                model.addAttribute("ListOfDish", DaGrassoList);
                String DaGrasso = "DaGrasso";
                model.addAttribute("Restaurant", DaGrasso);
                return "/showMenu";

        }
        return "/showMenu";
    }

}
