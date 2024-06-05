package ad.food2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import ad.food2.model.DaGrasso;
import ad.food2.model.Kfc;
import ad.food2.model.Macdonald;
import ad.food2.object.Basket;
import ad.food2.service.DishService;

public class DishControllerTest {

    @Mock
    private DishService dishService;

    @Mock
    private Model model;

    @Mock
    private Basket basket;

    @InjectMocks
    private DishController dishController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowMenuForMacdonald() {

        when(dishService.findMacdonaldList()).thenReturn(Arrays.asList(new Macdonald()));

        String viewName = dishController.showMenu("Macdonald", model);

        verify(model, times(1)).addAttribute(eq("ListOfDish"), anyList());

        verify(model, times(1)).addAttribute("Restaurant", "Macdonald");

        assertEquals("/showMenu", viewName);

    }

    @Test
    void testShowMenuForKfc() {

        when(dishService.findKfcList()).thenReturn(Arrays.asList(new Kfc()));

        String viewName = dishController.showMenu("Kfc", model);

        verify(model, times(1)).addAttribute(eq("ListOfDish"), anyList());

        verify(model, times(1)).addAttribute("Restaurant", "Kfc");

        assertEquals("/showMenu", viewName);

    }

    @Test
    void testShowMenuForDaGrasso() {

        when(dishService.findDaGrassoList()).thenReturn(Arrays.asList(new DaGrasso()));

        String viewName = dishController.showMenu("DaGrasso", model);

        verify(model, times(1)).addAttribute(eq("ListOfDish"), anyList());

        verify(model, times(1)).addAttribute("Restaurant", "DaGrasso");

        assertEquals("/showMenu", viewName);

    }

    @Test
    void testAddDishBasketToMacdonald() {
        Macdonald macdonald = new Macdonald();

        macdonald.setDish("Burger");
        BigDecimal price = new BigDecimal(10);
        macdonald.setPrice(price);
        macdonald.setName("Macdonald");

        when(dishService.findDishMacdonaldById(anyLong())).thenReturn(Optional.of(macdonald));

        String viewName = dishController.addDishToBasket("Macdonald", 1L, model);

        verify(basket, times(1)).addDish(any());
        assertEquals("/showMenu", viewName);

    }

    @Test
    void testAddDishBasketToKfc() {
        Kfc kfc = new Kfc();

        kfc.setDish("chicken");
        BigDecimal price = new BigDecimal(11);
        kfc.setPrice(price);
        kfc.setName("Kfc");

        when(dishService.findDishKfcById(anyLong())).thenReturn(Optional.of(kfc));

        String viewName = dishController.addDishToBasket("Kfc", 1L, model);

        verify(basket, times(1)).addDish(any());
        assertEquals("/showMenu", viewName);

    }

    @Test
    void testAddDishBasketToDaGrasso() {
        DaGrasso daGrass = new DaGrasso();

        daGrass.setDish("pizza");
        BigDecimal price = new BigDecimal(12);
        daGrass.setPrice(price);
        daGrass.setName("DaGrasso");

        when(dishService.findDishDaGrassoById(anyLong())).thenReturn(Optional.of(daGrass));

        String viewName = dishController.addDishToBasket("DaGrasso", 1L, model);

        verify(basket, times(1)).addDish(any());
        assertEquals("/showMenu", viewName);

    }

}
