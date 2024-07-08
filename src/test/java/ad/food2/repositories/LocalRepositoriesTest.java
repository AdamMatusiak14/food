package ad.food2.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ad.food2.model.Local;
import ad.food2.repository.LocalRepository;
import ad.food2.service.UsersService;

public class LocalRepositoriesTest {

    @Mock
    private LocalRepository localRepository;

    @InjectMocks
    private UsersService usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByReservationId() {
        Local local = new Local();
        local.setId(1L);
        List<Local> localList = Arrays.asList(local);

        when(localRepository.findByReservationId(1L)).thenReturn(localList);

        List<Local> result = localRepository.findByReservationId(1L);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());

    }

}
