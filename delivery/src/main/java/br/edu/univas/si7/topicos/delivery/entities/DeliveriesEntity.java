package br.edu.univas.si7.topicos.delivery.entities;

import java.io.Serializable;
import java.util.Date;

import br.edu.univas.si7.topicos.delivery.DeliveriesType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString


public class DeliveriesEntity implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String deliveryDateTime;
	private long CPFReceiver;
	private boolean customerConfirmation;
	private int attempt;
	private DeliveriesType deliveriesType;

}
