package univas.edu.br.si7.tpa.tabalho.Payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

import br.edu.univas.si7.topicos.payment.PaymentApplication;

@TestConfiguration(proxyBeanMethods = false)
public class TestPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.from(PaymentApplication::main).with(TestPaymentApplication.class).run(args);
	}

}
