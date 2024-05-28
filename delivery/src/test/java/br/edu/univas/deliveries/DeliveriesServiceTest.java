package br.edu.univas.deliveries;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;


import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.edu.univas.si7.topicos.deliveries.dto.DeliveriesDTO;
import br.edu.univas.si7.topicos.delivery.entities.DeliveriesEntity;
import br.edu.univas.si7.topicos.delivery.repositories.DeliveriesRepository;
import br.edu.univas.si7.topicos.delivery.service.DeliveriesService;
import br.edu.univas.si7.topicos.delivery.suporte.DeliveriesException;
import br.edu.univas.si7.topicos.delivery.util.DeliveriesEntityConverter;

public class DeliveriesServiceTest {
	
	@InjectMocks
	private static DeliveriesService service;

    @Mock
	private static DeliveriesRepository repoMock;

   

	
	@BeforeAll
	public static void setup() {
		MockitoAnnotations.openMocks(DeliveriesServiceTest.class);
		repoMock = Mockito.mock(DeliveriesRepository.class);
		service = new DeliveriesService(repoMock);
		
	}
	

	@Test
	void testGetAllDeliveries() {

		List<DeliveriesEntity> deliveries = List.of(
				new DeliveriesEntity()
				);
		Mockito.when(repoMock.findAll()).thenReturn(deliveries);

		List<DeliveriesDTO> allDeliveries = service.findAll();

		Mockito.verify(repoMock, Mockito.times(1)).findAll();

		assertNotNull(allDeliveries);
		assertEquals(1, allDeliveries.size());
	}

	@Test
	void testGetDeliveriesById() {
		DeliveriesEntity d1 = new DeliveriesEntity();
		d1.setId(1);
		Mockito.when(repoMock.findById(1)).thenReturn(Optional.of(d1));
		
		DeliveriesEntity deliveries = service.findById(1);
		assertNotNull(deliveries);
		assertEquals(1, deliveries.getId());
	}
	
	@Test
	void testGetDeliveriesById_Null() {
        assertThrows(IllegalArgumentException.class, () -> service.findById(null));
    }
	
	@Test
    void testCreateDeliveries() {
        DeliveriesDTO dto = new DeliveriesDTO();
        dto.setId(1);
       

        DeliveriesEntity entity = new DeliveriesEntity();
        entity.setId(1);
     

        
        service.createDeliveries(dto);

        
        Mockito.verify(repoMock, Mockito.times(1)).save(any(DeliveriesEntity.class));
    }
	
	@Test
    void testDeleteDeliveries() {
   
        DeliveriesEntity d1 = new DeliveriesEntity();
        d1.setId(1);
        Mockito.when(repoMock.findById(1)).thenReturn(Optional.of(d1));


        service.deleteDeliveries(1);

        Mockito.verify(repoMock, Mockito.times(1)).findById(1);
        Mockito.verify(repoMock, Mockito.times(1)).delete(d1);
    }
	
	@Test
    void testDeleteDeliveries_NullId() {
        assertThrows(DeliveriesException.class, () -> service.deleteDeliveries(null));
    }

	@Test
    void testFindByCustomerConfirmation() {
        DeliveriesEntity entity = new DeliveriesEntity();
        entity.setCustomerConfirmation(true);

        List<DeliveriesEntity> entities = List.of(entity);
        Mockito.when(repoMock.findByCustomerConfirmation(true)).thenReturn(entities);

        DeliveriesDTO dto = new DeliveriesDTO();
        dto.setCustomerConfirmation(true);

        try (var mockedConverter = Mockito.mockStatic(DeliveriesEntityConverter.class)) {
            mockedConverter.when(() -> DeliveriesEntityConverter.toDTO(entity)).thenReturn(dto);

            List<DeliveriesDTO> result = service.findByCustomerConfirmation(true);

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(true, result.get(0).isCustomerConfirmation());

            Mockito.verify(repoMock, Mockito.times(1)).findByCustomerConfirmation(true);
            mockedConverter.verify(() -> DeliveriesEntityConverter.toDTO(entity), Mockito.times(1));
        }
    }
}
