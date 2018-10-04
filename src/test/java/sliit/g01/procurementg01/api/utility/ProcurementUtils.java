package sliit.g01.procurementg01.api.utility;

import sliit.g01.procurementg01.api.model.*;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Viraj
 */
public class ProcurementUtils {


    protected final String ACCOUNTING_STAFF_REQUEST = "{\"employeeId\":\"EMP1021\",\"employeeType\":\"Permanent\"," +
            "\"employeeName\":\"Sunil Perera\",\"address\":null,\"email\":null,\"phone\":\"0765312121\"," +
            "\"nic\":\"950120034V\",\"category\":null}";

    protected final String SITE_MGR_REQUEST = "{\"managedSiteId\":\"ST1021\",\"nic\":\"950712321V\"," +
            "\"phone\":\"076678212\",\"address\":null,\"employeeId\":\"EMP12211\"}";

    protected final String SITE_REQUEST = "{\"siteId\":\"ST1021\",\"siteName\":\"Malabe\"," +
            "\"address\":\"27/g Malabe\",\"storageCapacity\":21,\"currentCapacity\":200}";

    protected final String PAYMENT_REQUEST = "{\"paymentID\":\"PA1021\",\"paymentMethod\":\"Cash\",\"totAmount\":10000}";

    protected final String RATING_REQUEST =  "{\"purchaseOrderReference\":null,\"supplierName\":\"Sunil Perera\",\"supplierId\":\"SUP1021\",\"constructorId\":null,\"constructorName\":null,\"deliveryEfficiency\":3, \"supportiveness\":5,\"workOnTime\":3,\"overallRate\":3,\"feedback\":null}";

    private AccountingStaff createAccountingStaffBean(String empId, String empName, String empType, String nic, String phone) {
        AccountingStaff accountingStaff = new AccountingStaff();
        accountingStaff.setEmployeeId(empId);
        accountingStaff.setEmployeeName(empName);
        accountingStaff.setEmployeeType(empType);
        accountingStaff.setNic(nic);
        accountingStaff.setPhone(phone);
        return accountingStaff;
    }

    private Rating createRatingBean(String constructorId, String supplierId, String constructorName, int delivery, int  wotime, int supportive) {
        Rating rating = new Rating();
        rating.setConstructorId(constructorId);
        rating.setSupplierId(supplierId);
        rating.setConstructorName(constructorName);
        rating.setDeliveryEfficiency(delivery);
        rating.setWorkOnTime(wotime);
        rating.setSupportiveness(supportive);
        return rating;
    }

    private SiteManager createSiteManagerBean(String manageSId, String nic, String phone, String empId) {
        SiteManager siteManager = new SiteManager();
        siteManager.setManagedSiteId(manageSId);
        siteManager.setNic(nic);
        siteManager.setPhone(phone);
        siteManager.setEmployeeId(empId);
        return siteManager;
    }

    private Site createSiteBean(String siteId, String siteName, String address, float storageCapacity, long currentCapacity) {
        Site site = new Site();
        site.setSiteId(siteId);
        site.setSiteName(siteName);
        site.setAddress(address);
        site.setStorageCapacity(storageCapacity);
        site.setCurrentCapacity(currentCapacity);
        return site;
    }

    private Payment createPaymentBean(String paymentId, String payMethod, double tot) {
        Payment payment = new Payment();
        payment.setTotAmount(tot);
        payment.setPaymentID(paymentId);
        payment.setPaymentMethod(payMethod);
        return payment;
    }

    protected List<AccountingStaff> getAccountingStaffBeans() {
        List<AccountingStaff> accountingStaffList = new ArrayList<>();
        accountingStaffList.add(createAccountingStaffBean("EMP1021","Sunil Perera", "Permanent", "950120034V", "0765312121"));
        accountingStaffList.add(createAccountingStaffBean("EMP1022","Nima Perera", "Permanent", "960120034V", "0775312331"));
        return accountingStaffList;
    }

    protected List<Rating> getSupplierRatingBeans() {
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(createRatingBean("","SUP1021", "", 3, 3, 5));
        ratingList.add(createRatingBean("","SUP1021", "", 4, 3, 4));
        return ratingList;
    }
    protected List<Rating> getConstructorRatingBeans() {
        List<Rating> ratingList = new ArrayList<>();
        ratingList.add(createRatingBean("CON1211","", "Nimal Perera", 0, 3, 5));
        ratingList.add(createRatingBean("CON1212","", "Sunil Silva", 0, 3, 4));
        return ratingList;
     }

    protected List<SiteManager> getSiteManagerBeans() {
        List<SiteManager> siteList = new ArrayList<>();
        siteList.add(createSiteManagerBean("ST1211","960760023V", "071231232", "EMP12211"));
        return siteList;
     }

    protected List<Site> getSiteBeans() {
        List<Site> siteManagerList = new ArrayList<>();
        siteManagerList.add(createSiteBean("ST1211","Malabe", "21/g Malabe", 12.0f, 90));
        return siteManagerList;
     }

     protected List<Payment> getPaymentBeans() {
        List<Payment> paymentList = new ArrayList<>();
         paymentList.add(createPaymentBean("PA1211","Cash", 10000));
        return paymentList;
     }

}
