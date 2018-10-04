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
import sliit.g01.procurementg01.api.model.Site;
import sliit.g01.procurementg01.api.service.impl.SiteManagerServiceImpl;
import sliit.g01.procurementg01.api.service.impl.SiteServiceImpl;
import sliit.g01.procurementg01.api.utility.ProcurementUtils;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class to check the REST calls associated with the Site
 * @author Viraj
 */

@RunWith(SpringRunner.class)
@WebMvcTest(SiteController.class)
public class SiteControllerTest extends ProcurementUtils {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SiteServiceImpl siteService;

    @MockBean
    private SiteManagerServiceImpl siteManagerService;

    @Test
    public void retrieveSiteDataBySiteId_Test() throws Exception {
        String siteId = "ST1022";
        List<Site> siteData = getSiteBeans();
        given(siteService.getSite(siteId)).willReturn(siteData.get(0));
        mvc.perform(get("/site/"+ siteId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(7)));
    }

    @Test
    public void deleteSiteData_Test() throws Exception {
            String siteId = "ST1022";
            given(siteService.deleteSite(siteId)).willReturn(true);
            mvc.perform(delete("/site/"+ siteId)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

    @Test
    public void retrieveAllSiteData_Test() throws Exception {
        List<Site> allSites = getSiteBeans();
        given(siteService.listAllSites()).willReturn(allSites);
        mvc.perform(get("/site/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].siteName", is(allSites.get(0).getSiteName())))
                .andExpect(jsonPath("$[0].address", is(allSites.get(0).getAddress())));
    }

    @Test
    public void saveSiteDataException_Test() throws Exception {
        mvc.perform(post("/site/add-new-site").content(SITE_REQUEST)
                                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is(501));
    }

    @Test
    public void updateSiteData_Test() throws Exception {
        String siteId = "ST1243";
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/site/" + siteId)
                        .contentType(APPLICATION_JSON_UTF8)
                        .accept(APPLICATION_JSON_UTF8)
                        .characterEncoding("UTF-8")
                .content(SITE_REQUEST);

        mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string(""))
                .andDo(MockMvcResultHandlers.print());
    }
}
