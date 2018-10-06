package sliit.g01.procurementg01.api.service;

import sliit.g01.procurementg01.api.model.Payment;

import java.util.List;

/**
 * @author Viraj
 */

public interface PaymentService {

	Payment makePayment(Payment payment);

	List<Payment> getAllPayments();

//	Payment getPaymentBySupplierID(String supplierId);

	Payment getPaymentByPaymentID(String paymentId);

	Boolean deletePayment(String paymentId);

	Payment updatePayment(String paymentId, Payment payment);

}
