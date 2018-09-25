package sliit.g01.procurementg01.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sliit.g01.procurementg01.api.model.Payment;
import sliit.g01.procurementg01.api.model.Rating;
import sliit.g01.procurementg01.api.repository.PaymentRepository;
import sliit.g01.procurementg01.api.repository.RatingRepository;
import sliit.g01.procurementg01.api.service.PaymentService;
import sliit.g01.procurementg01.api.service.RatingService;

import java.util.List;

/**
 * created by viraj
 **/

@Service("paymentService")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;


	@Override
	public Boolean makePayment(Payment payment) {
		return (paymentRepository.save(payment) != null);
	}

	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment getPaymentByPaymentID(String paymentId) {
		return paymentRepository.findByPaymentID(paymentId);
	}

//	@Override
//	public Payment getPaymentBySupplierID(String supplierId) {
//		return paymentRepository.findBySupplierID(supplierId);
//	}

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
