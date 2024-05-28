package br.edu.univas.si7.topicos.payment.entities;

import java.io.Serializable;
import java.util.Date;

import br.edu.univas.si7.topicos.payment.enums.PaymentStatus;
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
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter @Setter
@ToString
public class PaymentEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String method;
	private Date dueDate;
	private float amount;
	private Date paidAt;
	private int transactionId;
	private boolean active;
	
	
	@Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
