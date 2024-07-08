package ad.food2.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ad.food2.model.Client;
import ad.food2.repository.ClientRepository;
import ad.food2.service.UsersService;

public class ClientRepositoriesTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private UsersService usersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByNameAndPhone() {
        Client client = new Client();
        client.setName("John Doe");
        client.setPhone("12345");

        when(clientRepository.findByNameAndPhone("John Doe", "12345")).thenReturn(Optional.of(client));

        Optional<Client> result = clientRepository.findByNameAndPhone("John Doe", "12345");

        assertTrue(result.isPresent());
        assertEquals("John Doe", result.get().getName());

    }
}
