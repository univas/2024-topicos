package br.edu.univas.si7.topicos.product;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.edu.univas.si7.topicos.product.dtos.ProductDTO;
import br.edu.univas.si7.topicos.product.entities.ProductEntity;
import br.edu.univas.si7.topicos.product.reposioties.ProductRepository;
import br.edu.univas.si7.topicos.product.service.ProductService;

public class ProductServiceTest {

	private static ProductService service;
	private static ProductRepository repoMock;

	@BeforeAll
	public static void setup() {
		repoMock = Mockito.mock(ProductRepository.class);
		service = new ProductService(repoMock);
	}

	@Test
	void testGetAllProducts() {

		List<ProductEntity> products = List.of(
				new ProductEntity()
				);
		Mockito.when(repoMock.findAll()).thenReturn(products);

		List<ProductDTO> allProducts = service.findAll();

		Mockito.verify(repoMock, Mockito.times(1)).findAll();

		assertNotNull(allProducts);
		assertEquals(1, allProducts.size());
	}

	@Test
	void testGetProductById() {
		
		ProductEntity p1 = new ProductEntity();
		p1.setCode(1);
		Mockito.when(repoMock.findById(1)).thenReturn(Optional.of(p1));
		
		ProductEntity product = service.findById(1);
		assertNotNull(product);
		assertEquals(1, product.getCode());
	}

	@Test
	public void testFindByActive() {
		fail("Not yet implemented");
	}
}
