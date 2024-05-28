package br.edu.univas.si7.topicos.payment.validator;

import java.util.Date;

import br.edu.univas.si7.topicos.payment.dto.PaymentDTO;
import br.edu.univas.si7.topicos.payment.enums.PaymentStatus;

public class PaymentValidator {
	
	public static boolean validatePaymentStatusUpdateIsPaidOrCanceled(PaymentDTO payment) {
	    return payment.getStatus() != PaymentStatus.CANCELED && payment.getStatus() != PaymentStatus.PAID && payment.getStatus() != null;
	}
	
	public static boolean validatePaymentStatusUpdateIsPaid(PaymentDTO payment) {
	    return payment.getStatus() ==  PaymentStatus.PAID && payment.getStatus() != null;
	}

	
	public static boolean validatePaymentStatusUpdateCanceled(PaymentDTO payment) {
	    return payment.getStatus() != PaymentStatus.CANCELED;
	}

	
    public static boolean validatePayment(PaymentDTO payment) {
        return validateMethod(payment.getMethod()) &&
               validateDueDate(payment.getDueDate()) &&
               validateAmount(payment.getAmount()) &&
               validateTransactionId(payment.getTransactionId());
    }

    private static boolean validateMethod(String method) {
        return method != null && !method.trim().isEmpty();
    }

    private static boolean validateDueDate(Date dueDate) {
        return dueDate != null;
    }

    private static boolean validateAmount(float amount) {
        return amount > 0;
    }

    private static boolean validateTransactionId(int transactionId) {
        return transactionId > 0;
    }
}

