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
import sliit.g01.procurementg01.api.model.SiteManager;
import sliit.g01.procurementg01.api.service.impl.AccountingStaffServiceImpl;
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
 * Test class to check the REST calls associated with the Site Manager
 *
 * @author Viraj
 */

@RunWith(SpringRunner.class)
@WebMvcTest(SiteManagerController.class)
public class SiteManagerControllerTest extends ProcurementUtils {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SiteManagerServiceImpl siteManagerService;


    @Test
    public void retrieveAllSiteManagerData_Test() throws Exception {
        List<SiteManager> allSiteManagers = getSiteManagerBeans();
        given(siteManagerService.getAllSiteManagers()).willReturn(allSiteManagers);
        mvc.perform(get("/employee/site-manager")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].managedSiteId", is(allSiteManagers.get(0).getManagedSiteId())))
                .andExpect(jsonPath("$[0].employeeId", is(allSiteManagers.get(0).getEmployeeId())))
                .andExpect(jsonPath("$[0].nic", is(allSiteManagers.get(0).getNic())));
    }

    @Test
    public void retrieveSiteManagerDataBySiteId_Test() throws Exception {
        String siteId = "ST1022";
        List<SiteManager> managedSiteMgr = getSiteManagerBeans();
        given(siteManagerService.getSiteManagerOfSite(siteId)).willReturn(managedSiteMgr.get(0));
        mvc.perform(get("/employee/site-manager/sites/" + siteId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(8)));
    }


    @Test
    public void saveSiteManagerData_Test() throws Exception {
        mvc.perform(post("/employee/site-manager").content(SITE_MGR_REQUEST)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

    }

    @Test
    public void saveSiteManagerDataException_Test() throws Exception {
        mvc.perform(post("/employee/site-manager").content(SITE_MGR_REQUEST)
                .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is(200));

    }

    @Test
    public void updateAccountingStaffData_Test() throws Exception {
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put("/employee/site-manager/assign")
                        .contentType(APPLICATION_JSON_UTF8)
                        .accept(APPLICATION_JSON_UTF8)
                        .characterEncoding("UTF-8")
                        .content(SITE_MGR_REQUEST);

        mvc.perform(builder)
                .andExpect(MockMvcResultMatchers.status()
                        .isOk())
                .andExpect(MockMvcResultMatchers.content()
                        .string("Success:false"))
                .andDo(MockMvcResultHandlers.print());
    }
}
