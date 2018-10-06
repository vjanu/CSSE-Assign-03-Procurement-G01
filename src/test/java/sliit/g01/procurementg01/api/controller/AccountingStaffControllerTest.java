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
import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Rating;
import sliit.g01.procurementg01.api.service.impl.AccountingStaffServiceImpl;
import sliit.g01.procurementg01.api.utility.ProcurementUtils;

import java.util.Arrays;
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
 * Test class to check the REST calls associated with the Accounting Staff
 * @author Viraj
 */

@RunWith(SpringRunner.class)
@WebMvcTest(AccountingStaffController.class)
public class AccountingStaffControllerTest extends ProcurementUtils {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountingStaffServiceImpl accountingStaffService;


    @Test
    public void retrieveAllAccountingStaffData_Test() throws Exception {
        List<AccountingStaff> allAccountingStaff = getAccountingStaffBeans();
        given(accountingStaffService.listAccountingStaff()).willReturn(allAccountingStaff);
        mvc.perform(get("/employee/accounting-staff")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].employeeId", is(allAccountingStaff.get(0).getEmployeeId())))
                .andExpect(jsonPath("$[0].employeeName", is(allAccountingStaff.get(0).getEmployeeName())));
    }

    @Test
    public void saveAccountingStaffData_Test() throws Exception {
        mvc.perform(post("/employee/accounting-staff").content(ACCOUNTING_STAFF_REQUEST)
                                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    }

    @Test
    public void saveAccountingStaffDataException_Test() throws Exception {
        mvc.perform(post("/employee/accounting-staff/2").content(ACCOUNTING_STAFF_REQUEST)
                                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is(405));

    }

    @Test
    public void updateAccountingStaffData_Test() throws Exception {
        long id = 1;
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/employee/accounting-staff/" + id)
                        .contentType(APPLICATION_JSON_UTF8)
                        .accept(APPLICATION_JSON_UTF8)
                        .characterEncoding("UTF-8")
                .content(ACCOUNTING_STAFF_REQUEST);

        mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(""))
                .andDo(MockMvcResultHandlers.print());
    }
}
