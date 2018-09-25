package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import sliit.g01.procurementg01.api.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {
	Payment findBySupplierID(String supplierId);

	Payment findByPaymentID(String paymentId);
}
