package sliit.g01.procurementg01.api.controller;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Payment;
import sliit.g01.procurementg01.api.service.impl.AccountingStaffServiceImpl;
import sliit.g01.procurementg01.api.service.impl.PaymentServiceImpl;

import java.util.List;

/**
 * @author Viraj
 */

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentServiceImpl paymentService;

	@RequestMapping(method = RequestMethod.POST)
	public Payment createPayment(@Validated @RequestBody final Payment payment) {
		payment.setPaymentID("PA" + RandomStringUtils.randomNumeric(5));
		return paymentService.makePayment(payment);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Payment> retrievePayments() {
		return paymentService.getAllPayments();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Payment retrievePaymentById(@PathVariable("id") final String id) {
		return paymentService.getPaymentByPaymentID(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Payment updatePayment(@PathVariable("id") final String id, @Validated @RequestBody final Payment payment) {
		return paymentService.updatePayment(id, payment);
	}
}
