package br.edu.univas.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.edu.univas.si7.topicos.customer.dto.CustomerDTO;
import br.edu.univas.si7.topicos.customer.entities.CustomerEntity;
import br.edu.univas.si7.topicos.customer.enumeration.CustomerType;
import br.edu.univas.si7.topicos.customer.repositories.CustomerRepository;
import br.edu.univas.si7.topicos.customer.service.CustomerService;
import br.edu.univas.si7.topicos.customer.support.ObjectNotFoundException;
import br.edu.univas.si7.topicos.customer.util.CustomerEntityConverter;

public class CustomerServiceTest {

    private static CustomerService service;
    private static CustomerRepository repoMock;

    @BeforeAll
    public static void setup() {

    }

    @Test
    void shouldGetAllCustomersSuccessfully() {
        CustomerEntityConverter converter = new CustomerEntityConverter();
        repoMock = Mockito.mock(CustomerRepository.class);
        service = new CustomerService(repoMock, converter);

        List<CustomerEntity> customers = List.of(new CustomerEntity());
        Mockito.when(repoMock.findAll()).thenReturn(customers);

        List<CustomerDTO> allCustomers = service.findAll();

        Mockito.verify(repoMock, Mockito.times(1)).findAll();

        assertNotNull(allCustomers);
        assertEquals(1, allCustomers.size());
    }

    @Test
    void shouldGetCustomerByIdSuccessfully() {
        CustomerEntityConverter converter = new CustomerEntityConverter();
        repoMock = Mockito.mock(CustomerRepository.class);
        service = new CustomerService(repoMock, converter);

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1);
        Mockito.when(repoMock.findById(1)).thenReturn(Optional.of(customerEntity));

        CustomerEntity customer = service.findById(1);

        assertNotNull(customer);
        assertEquals(1, customer.getId());
    }

    @Test
    void shouldCreateCustomerSuccessfully() {
        CustomerEntityConverter converter = new CustomerEntityConverter();
        repoMock = Mockito.mock(CustomerRepository.class);
        service = new CustomerService(repoMock, converter);

        CustomerDTO newCustomerDTO = new CustomerDTO();
//        newCustomerDTO.setId(1);
        newCustomerDTO.setName("New Customer");
        newCustomerDTO.setEmail("newcustomer@example.com");
        newCustomerDTO.setPhoneNumber("40028922");
        newCustomerDTO.setDocumentNumber(12365478890L);
        newCustomerDTO.setType(CustomerType.PHISICPERSON);

        service.createCustomer(newCustomerDTO);
        verify(repoMock, times(1)).save(Mockito.any(CustomerEntity.class));
    }

    @Test
    void shouldUpdateCustomerSuccessfully() {
        CustomerEntityConverter converter = new CustomerEntityConverter();
        repoMock = Mockito.mock(CustomerRepository.class);
        service = new CustomerService(repoMock, converter);

        CustomerEntity existingCustomer = new CustomerEntity();
        existingCustomer.setId(1);
        existingCustomer.setName("Existing Customer");
        existingCustomer.setEmail("existingcustomer@example.com");

        CustomerDTO updatedCustomerDTO = new CustomerDTO();
//        updatedCustomerDTO.setId(1);
        updatedCustomerDTO.setName("Updated Customer");
        updatedCustomerDTO.setEmail("updatedcustomer@example.com");

        when(repoMock.findById(1)).thenReturn(Optional.of(existingCustomer));
        service.updateCustomer(updatedCustomerDTO, 1);

        verify(repoMock, times(1)).save(Mockito.any(CustomerEntity.class));
    }

    @Test
    void shouldDeleteCustomerSuccessfully() {
        CustomerEntityConverter converter = new CustomerEntityConverter();
        repoMock = Mockito.mock(CustomerRepository.class);
        service = new CustomerService(repoMock, converter);

        CustomerEntity existingCustomer = new CustomerEntity();
        existingCustomer.setId(1);
        when(repoMock.findById(1)).thenReturn(Optional.of(existingCustomer));

        service.deleteCustomer(1);

        verify(repoMock, times(1)).delete(Mockito.any(CustomerEntity.class));
    }

    @Test
    void shouldErrorGetCustomerByIdWhenNotFound() {
        CustomerEntityConverter converter = new CustomerEntityConverter();
        repoMock = Mockito.mock(CustomerRepository.class);
        service = new CustomerService(repoMock, converter);

        Mockito.when(repoMock.findById(2)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            service.findById(2);
        });
    }

    @Test
    void shouldErrorCreateCustomerWhenEmailIsNull() {
        CustomerEntityConverter converter = new CustomerEntityConverter();
        repoMock = Mockito.mock(CustomerRepository.class);
        service = new CustomerService(repoMock, converter);

        CustomerDTO newCustomerDTO = new CustomerDTO();
//        newCustomerDTO.setId(1);
        newCustomerDTO.setName("New Customer");
        newCustomerDTO.setPhoneNumber("40028922");
        newCustomerDTO.setDocumentNumber(12365478890L);

        assertThrows(IllegalArgumentException.class, () -> {
            service.createCustomer(newCustomerDTO);
        });
    }

    @Test
    void shouldErrorUpdateCustomerWhenNotFound() {
        CustomerEntityConverter converter = new CustomerEntityConverter();
        repoMock = Mockito.mock(CustomerRepository.class);
        service = new CustomerService(repoMock, converter);

        CustomerDTO updatedCustomerDTO = new CustomerDTO();
//        updatedCustomerDTO.setId(1);
        updatedCustomerDTO.setName("Updated Customer");
        updatedCustomerDTO.setEmail("updatedcustomer@example.com");

        Mockito.when(repoMock.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            service.updateCustomer(updatedCustomerDTO, 1);
        });
    }

    @Test
    void shouldErrorDeleteCustomerWhenNotFound() {
        CustomerEntityConverter converter = new CustomerEntityConverter();
        repoMock = Mockito.mock(CustomerRepository.class);
        service = new CustomerService(repoMock, converter);

        Mockito.when(repoMock.findById(1)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> {
            service.deleteCustomer(1);
        });
    }
}
