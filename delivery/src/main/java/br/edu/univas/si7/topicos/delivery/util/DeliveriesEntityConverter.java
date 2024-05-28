package br.edu.univas.si7.topicos.delivery.util;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.springframework.stereotype.Component;

import br.edu.univas.si7.topicos.deliveries.dto.DeliveriesDTO;
import br.edu.univas.si7.topicos.delivery.entities.DeliveriesEntity;

@Component
public class DeliveriesEntityConverter {
	
	public static DeliveriesDTO toDTO (DeliveriesEntity deliveries) {
        Date dataHoraAtual = new Date();
        String dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dataHoraAtual);
	
		return new DeliveriesDTO(
					deliveries.getId(), dataFormatada,
					deliveries.getCPFReceiver(), deliveries.isCustomerConfirmation(),
					deliveries.getAttempt(), deliveries.getDeliveriesType());
	}
	
	public DeliveriesEntity toEntity(DeliveriesDTO deli) {
		System.out.println("toEntity: " + deli);
		return new DeliveriesEntity(deli.getId(), deli.getDeliveryDateTime(),
				deli.getCPFReceiver(), deli.isCustomerConfirmation(),
				deli.getAttempt(), deli.getDeliveriesType());
	}

}
