package sliit.g01.procurementg01.api.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sliit.g01.procurementg01.api.model.Payment;
import sliit.g01.procurementg01.api.model.SiteManager;
import sliit.g01.procurementg01.api.service.impl.PaymentServiceImpl;
import sliit.g01.procurementg01.api.service.impl.SiteManagerServiceImpl;
import sliit.g01.procurementg01.api.utility.ProcurementUtils;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class to check the REST calls associated with the Payment
 * @author Viraj
 */

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentsControllerTest extends ProcurementUtils {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PaymentServiceImpl paymentService;


    @Test
    public void retrieveAllPaymentData_Test() throws Exception {
        List<Payment> allPayments = getPaymentBeans();
        given(paymentService.getAllPayments()).willReturn(allPayments);
        mvc.perform(get("/payment")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].paymentID", is(allPayments.get(0).getPaymentID())))
                .andExpect(jsonPath("$[0].paymentMethod", is(allPayments.get(0).getPaymentMethod())))
                .andExpect(jsonPath("$[0].totAmount", is(allPayments.get(0).getTotAmount())));
    }

    @Test
    public void retrievePaymentById_Test() throws Exception {
        String payId = "ST1022";
        List<Payment> payments = getPaymentBeans();
        given(paymentService.getPaymentByPaymentID(payId)).willReturn(payments.get(0));
        mvc.perform(get("/payment/"+ payId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(3)));
    }


    @Test
    public void savePaymentData_Test() throws Exception {
        mvc.perform(post("/payment").content(PAYMENT_REQUEST)
                                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    }

    @Test
    public void savePaymentException_Test() throws Exception {
        mvc.perform(post("/payment").content(PAYMENT_REQUEST)
                                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is(200));

    }

    @Test
    public void updatePaymentData_Test() throws Exception {
        String payId = "ST1022";
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/payment/"+payId)
                        .contentType(APPLICATION_JSON_UTF8)
                        .accept(APPLICATION_JSON_UTF8)
                        .characterEncoding("UTF-8")
                .content(PAYMENT_REQUEST);

        mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(""))
                .andDo(MockMvcResultHandlers.print());
    }
}
