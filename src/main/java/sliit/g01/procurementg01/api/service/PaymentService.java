package sliit.g01.procurementg01.api.service;

import sliit.g01.procurementg01.api.model.Payment;

import java.util.List;

/**
 * created by viraj
 **/

public interface PaymentService {

	Boolean makePayment(Payment payment);

	List<Payment> getAllPayments();

	Payment getPaymentBySupplierID(String supplierId);

	Boolean deletePayment(String paymentId);

	Payment updatePayment(String paymentId, Payment payment);

}
