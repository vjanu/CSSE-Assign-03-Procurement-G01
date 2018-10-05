package sliit.g01.procurementg01.api.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import sliit.g01.procurementg01.api.service.impl.RequestMaterialServiceImpl;
import sliit.g01.procurementg01.api.utility.RequestMaterialUtils;

/**
 * Test class to check the REST calls associated with the Request Material
 * 
 * @author Tharindu TCJ
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(RequestMaterialController.class)
// @ContextConfiguration("/applicationContext.xml")
public class RequestMaterialControllerTest extends RequestMaterialUtils {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private RequestMaterialServiceImpl requestMaterialServiceImpl;

	@Test
	public void saveSiteDataException_Test() throws Exception {
		mvc.perform(post("/requestmaterial/add-new-request").content(ADD_NEW_REQUEST_JSON)
				.contentType(APPLICATION_JSON_UTF8)).andExpect(status().is(400));
	}

	@Test
	public void approveRequestedMaterial_Test() throws Exception {
		String requestId = "MR92761";

		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/requestmaterial/update/" + requestId)
				.contentType(APPLICATION_JSON_UTF8).accept(APPLICATION_JSON_UTF8).characterEncoding("UTF-8")
				.content(PROCUREMENT_STAFF_APPROVED_JSON);

		mvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string("false")).andDo(MockMvcResultHandlers.print());
	}

}
