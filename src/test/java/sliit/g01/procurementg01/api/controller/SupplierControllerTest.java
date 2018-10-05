package sliit.g01.procurementg01.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import sliit.g01.procurementg01.api.service.impl.SupplierServiceImpl;
import sliit.g01.procurementg01.api.utility.SupplierUtils;

/**
 * @author Tharindu TCJ
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SupplierController.class)
public class SupplierControllerTest extends SupplierUtils {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private SupplierServiceImpl supplierService;

	@Test
	public void blacklistSupplier_Test() throws Exception {
		String supplierId = "SP10980";

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/supplier/update/" + supplierId)
				.contentType(APPLICATION_JSON_UTF8).accept(APPLICATION_JSON_UTF8).characterEncoding("UTF-8")
				.content(BLACKLIST_JSON);

		mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("true")).andDo(MockMvcResultHandlers.print());
	}

	@Test
	public void unBannedSupplier_Test() throws Exception {
		String supplierId = "SP57641";

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/supplier/update/" + supplierId)
				.contentType(APPLICATION_JSON_UTF8).accept(APPLICATION_JSON_UTF8).characterEncoding("UTF-8")
				.content(UNBANNED_JSON);

		mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("")).andDo(MockMvcResultHandlers.print());
	}

}
