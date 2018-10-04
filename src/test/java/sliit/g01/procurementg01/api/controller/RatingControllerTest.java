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
import sliit.g01.procurementg01.api.service.impl.RatingServiceImpl;
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
 * Test class to check the REST calls associated with the Rating
 * @author Viraj
 */

@RunWith(SpringRunner.class)
@WebMvcTest(RatingController.class)
public class RatingControllerTest extends ProcurementUtils {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RatingServiceImpl ratingService;


    @Test
    public void retrieveAllSupplierRatingData_Test() throws Exception {
        List<Rating> allSupplierRating = getSupplierRatingBeans();
        given(ratingService.getSupplierRatings()).willReturn(allSupplierRating);
        mvc.perform(get("/ratings/supplier-ratings")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].supplierId", is(allSupplierRating.get(0).getSupplierId())))
                .andExpect(jsonPath("$[0].workOnTime", is(allSupplierRating.get(0).getWorkOnTime())))
                .andExpect(jsonPath("$[0].deliveryEfficiency", is(allSupplierRating.get(0).getDeliveryEfficiency())));
    }

    @Test
    public void retrieveAllConstructorRatingData_Test() throws Exception {
        List<Rating> allConstructorRating = getConstructorRatingBeans();
        given(ratingService.getSupplierRatings()).willReturn(allConstructorRating);
        mvc.perform(get("/ratings/constructor-ratings")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].constructorId", is(allConstructorRating.get(0).getConstructorId())))
                .andExpect(jsonPath("$[0].constructorName", is(allConstructorRating.get(0).getConstructorName())))
                .andExpect(jsonPath("$[0].workOnTime", is(allConstructorRating.get(0).getWorkOnTime())))
                .andExpect(jsonPath("$[0].deliveryEfficiency", is(allConstructorRating.get(0).getDeliveryEfficiency())));
    }


    @Test
    public void saveSupplierRatingDataException_Test() throws Exception {
        mvc.perform(post("/ratings").content(RATING_REQUEST)
                                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is(501));

    }


}
