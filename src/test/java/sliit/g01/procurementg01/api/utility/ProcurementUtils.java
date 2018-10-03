package sliit.g01.procurementg01.api.utility;

import sliit.g01.procurementg01.api.model.AccountingStaff;
import sliit.g01.procurementg01.api.model.Rating;

import java.util.ArrayList;
import java.util.List;
/**
 * @author Viraj
 */
public class ProcurementUtils {


    protected final String ACCOUNTING_STAFF_REQUEST = "{\"employeeId\":\"EMP1021\",\"employeeType\":\"Permanent\"," +
            "\"employeeName\":\"Sunil Perera\",\"address\":null,\"email\":null,\"phone\":\"0765312121\"," +
            "\"nic\":\"950120034V\",\"category\":null}";

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

}
