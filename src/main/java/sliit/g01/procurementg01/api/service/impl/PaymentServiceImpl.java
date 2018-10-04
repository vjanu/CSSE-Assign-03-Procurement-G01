package sliit.g01.procurementg01.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Payment;
import sliit.g01.procurementg01.api.repository.PaymentRepository;
import sliit.g01.procurementg01.api.service.PaymentService;

/**
 * @author Viraj
 */

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public Payment makePayment(Payment payment) {
		return paymentRepository.save(payment);
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment getPaymentByPaymentID(String paymentId) {
		return paymentRepository.findByPaymentID(paymentId);
	}

	// @Override
	// public Payment getPaymentBySupplierID(String supplierId) {
	// return paymentRepository.findBySupplierID(supplierId);
	// }

	@Override
	public Boolean deletePayment(String paymentId) {
		return null;
	}

	@Override
	public Payment updatePayment(String paymentId, Payment Payment) {
		Payment newPayment = paymentRepository.findByPaymentID(paymentId);

		if (Payment.getTotAmount() == 0)
			Payment.setTotAmount(newPayment.getTotAmount());

		return paymentRepository.save(Payment);
	}
}
