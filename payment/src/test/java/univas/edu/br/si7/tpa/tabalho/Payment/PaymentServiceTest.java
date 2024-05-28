package univas.edu.br.si7.tpa.tabalho.Payment;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.edu.univas.si7.topicos.payment.dto.PaymentDTO;
import br.edu.univas.si7.topicos.payment.entities.PaymentEntity;
import br.edu.univas.si7.topicos.payment.enums.PaymentStatus;
import br.edu.univas.si7.topicos.payment.repositories.PaymentRepository;
import br.edu.univas.si7.topicos.payment.service.PaymentService;
import br.edu.univas.si7.topicos.payment.util.PaymentEntityConverter;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

public class PaymentServiceTest {

    private static PaymentService service;
    private static PaymentRepository repoMock;
    private static PaymentEntityConverter converterMock;


    @BeforeAll
    public static void setup() {
        repoMock = Mockito.mock(PaymentRepository.class);
        converterMock = Mockito.mock(PaymentEntityConverter.class);
        service = new PaymentService(repoMock, converterMock);  // Pass the mocked converter here
    }

    @Test
    void testGetAllPayments() {
        List<PaymentEntity> payments = List.of(new PaymentEntity(1, "Cartão", new java.util.Date(), 7.30f, null, 1, true, PaymentStatus.PENDING));
        Mockito.when(repoMock.findAllByActiveTrue()).thenReturn(payments);

        List<PaymentDTO> allPayments = service.findAll();

        Mockito.verify(repoMock, Mockito.times(1)).findAllByActiveTrue();
        assertNotNull(allPayments);
        assertEquals(1, allPayments.size());
    }

    @Test
    void testGetPaymentById() {
        PaymentEntity p1 = new PaymentEntity(1, "Cartão", new java.util.Date(), 7.30f, null, 1, true, PaymentStatus.PENDING);
        Mockito.when(repoMock.findByIdAndActiveTrue(1)).thenReturn(Optional.of(p1));

        PaymentEntity payment = service.findById(1);

        assertNotNull(payment);
        assertEquals(1, payment.getId());
    }

    @Test
    void testCreatePayment() {
        PaymentDTO dto = new PaymentDTO(0, "Cartão", new java.util.Date(), 7.30f, null, 1, true, PaymentStatus.PENDING);
        PaymentEntity entity = new PaymentEntity(1, "Cartão", new java.util.Date(), 7.30f, null, 1, true, PaymentStatus.PENDING);
        Mockito.when(converterMock.toEntity(any(PaymentDTO.class))).thenReturn(entity);
        Mockito.when(repoMock.save(any(PaymentEntity.class))).thenReturn(entity);

        PaymentDTO result = service.createPayment(dto);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }

    @Test
    void testUpdatePayment() {
        PaymentDTO dto = new PaymentDTO(1, "Dinheiro", new java.util.Date(), 15.0f, null, 1, true, PaymentStatus.PAID);
        PaymentEntity existing = new PaymentEntity(1, "Cartão", new java.util.Date(), 7.30f, null, 1, true, PaymentStatus.PENDING);
        PaymentEntity updated = new PaymentEntity(1, "Dinheiro", new java.util.Date(), 15.0f, null, 1, true, PaymentStatus.PAID);

        Mockito.when(repoMock.findByIdAndActiveTrue(1)).thenReturn(Optional.of(existing));
        Mockito.when(repoMock.save(any(PaymentEntity.class))).thenReturn(updated);

        service.updatePayment(dto, 1);

        Mockito.verify(repoMock).save(existing);  // Verify that the existing object was saved after the update
        assertEquals(PaymentStatus.PAID, existing.getStatus());
        assertEquals(15.0f, existing.getAmount(), 0.01);
    }
}