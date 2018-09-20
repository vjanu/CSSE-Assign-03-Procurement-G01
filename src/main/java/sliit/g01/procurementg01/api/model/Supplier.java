package sliit.g01.procurementg01.api.model;

import com.fasterxml.jackson.databind.annotation.JsonAppend.Prop;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Supplier")
public class Supplier {
    private String supplierId;
    private String supplierName;
    private int bankAccountNo;
    private String address;
    private String email;
    private String phone;
    private boolean availability;

    

}
