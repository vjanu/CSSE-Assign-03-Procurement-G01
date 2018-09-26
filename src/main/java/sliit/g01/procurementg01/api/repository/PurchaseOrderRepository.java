package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import sliit.g01.procurementg01.api.model.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderRepository extends MongoRepository<PurchaseOrder, String> {

    PurchaseOrder getPurchaseOrderByOrderId(String orderId);
    List<PurchaseOrder> getPurchaseOrdersByRequestId(String requestId); // orders which belong to the same material request.

}
