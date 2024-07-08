package ad.food2.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import ad.food2.model.DaGrasso;
import ad.food2.model.Kfc;
import ad.food2.model.Macdonald;
import ad.food2.repository.DaGrassoRepository;
import ad.food2.repository.KfcRepository;
import ad.food2.repository.MacdonaldRepository;

public class DishServiceTest {

    @Mock
    MacdonaldRepository macdonaldRepository;

    @Mock
    KfcRepository kfcRepository;

    @Mock
    DaGrassoRepository daGrassoRepository;

    @Mock
    Model model;

    @InjectMocks
    DishService dishService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindMacdonalList() {

        Macdonald dish1 = new Macdonald();
        Macdonald dish2 = new Macdonald();

        List<Macdonald> macdonaldList = Arrays.asList(dish1, dish2);

        when(macdonaldRepository.findAll()).thenReturn(macdonaldList);

        List<Macdonald> result = dishService.findMacdonaldList();

        assertEquals(2, result.size());

    }

    @Test
    void testFindKfcList() {

        Kfc dish1 = new Kfc();
        Kfc dish2 = new Kfc();

        List<Kfc> kfcList = Arrays.asList(dish1, dish2);

        when(kfcRepository.findAll()).thenReturn(kfcList);

        List<Kfc> result = dishService.findKfcList();

        assertEquals(2, result.size());

    }

    @Test
    void testFindDaGrassoList() {

        DaGrasso dish1 = new DaGrasso();
        DaGrasso dish2 = new DaGrasso();

        List<DaGrasso> daGrassoList = Arrays.asList(dish1, dish2);

        when(daGrassoRepository.findAll()).thenReturn(daGrassoList);

        List<DaGrasso> result = dishService.findDaGrassoList();

        assertEquals(2, result.size());

    }

    @Test
    void testFindDishMacdonaldById() {
        Macdonald macdonald = new Macdonald();
        macdonald.setId(1L);
        Optional<Macdonald> optionalDish = Optional.of(macdonald);

        when(macdonaldRepository.findById(1L)).thenReturn(optionalDish);

        Optional<Macdonald> result = dishService.findDishMacdonaldById(1L);

        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindDishKfcById() {
        Kfc kfc = new Kfc();
        kfc.setId(1L);
        Optional<Kfc> optionalDish = Optional.of(kfc);

        when(kfcRepository.findById(1L)).thenReturn(optionalDish);

        Optional<Kfc> result = dishService.findDishKfcById(1L);

        assertEquals(1L, result.get().getId());
    }

    @Test
    void testFindDishDaGrassoById() {
        DaGrasso dagrasso = new DaGrasso();
        dagrasso.setId(1L);
        Optional<DaGrasso> optionalDish = Optional.of(dagrasso);

        when(daGrassoRepository.findById(1L)).thenReturn(optionalDish);

        Optional<DaGrasso> result = dishService.findDishDaGrassoById(1L);

        assertEquals(1L, result.get().getId());
    }

    @Test
    void testShowMenu_Macdonald() {

        List<Macdonald> macdonaldList = Arrays.asList(mock(Macdonald.class));
        String mac = "Macdonald";

        when(macdonaldRepository.findAll()).thenReturn(macdonaldList);

        String viewName = dishService.showMenu(mac, model);

        verify(model).addAttribute(eq("ListOfDish"), anyList());
        verify(model).addAttribute("Restaurant", mac);

        assertEquals(viewName, "/showMenu");

    }

    @Test
    void testShowMenu_Kfc() {

        List<Kfc> kfcList = Arrays.asList(mock(Kfc.class));
        String kfc = "Kfc";

        when(kfcRepository.findAll()).thenReturn(kfcList);

        String viewName = dishService.showMenu(kfc, model);

        verify(model).addAttribute(eq("ListOfDish"), anyList());
        verify(model).addAttribute("Restaurant", kfc);

        assertEquals(viewName, "/showMenu");

    }

    @Test
    void testShowMenu_DaGrasso() {

        List<DaGrasso> daGrassoList = Arrays.asList(mock(DaGrasso.class));
        String daGrasso = "DaGrasso";

        when(daGrassoRepository.findAll()).thenReturn(daGrassoList);

        String viewName = dishService.showMenu(daGrasso, model);

        verify(model).addAttribute(eq("ListOfDish"), anyList());
        verify(model).addAttribute("Restaurant", daGrasso);

        assertEquals(viewName, "/showMenu");

    }
}
