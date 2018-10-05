package sliit.g01.procurementg01.api.utility;

import java.util.ArrayList;
import java.util.List;

import sliit.g01.procurementg01.api.model.RequestMaterial;

/**
 * @author Tharindu TCJ
 */
public class RequestMaterialUtils {

	protected final String PROCUREMENT_STAFF_APPROVED_JSON = "{\"siteId\":\"ST1021\"}";

	// protected final String ADD_NEW_REQUEST_JSON =
	// "{\"siteId\":\"ST1021\",\"siteName\":\"Malabe\","
	// + "\"address\":\"27/g
	// Malabe\",\"storageCapacity\":21,\"currentCapacity\":200}";

	protected final String ADD_NEW_REQUEST_JSON = "{\"requestId\":\"OR0126\",\"requestedPerson\":\"Amala\",\"siteId\":\"S004\",\"requestedDate\":\"2018-08-03\","
			+ "\"items\":{\"cemenet\":\"500KG\",\"sand\":\"202KG\",\"bricks\":\"1000\"},\"isImmediated\":\"1\","
			+ "isSiteManagerApproved\":\"1\",\"isProcumentApproved\":\"0\"}";

	private RequestMaterial createRequestMaterialBean(String requestId, String requestedPerson, String siteId,
			String requestedDate, String isImmediated, Boolean isProcumentApproved, String isSiteManagerApproved) {

		RequestMaterial req = new RequestMaterial();
		req.setRequestId(requestId);
		req.setRequestedPerson(requestedPerson);
		req.setSiteId(siteId);
		req.setRequestedDate(requestedDate);
		req.setIsImmediated(isImmediated);
		req.setIsProcumentApproved(isProcumentApproved);
		req.setIsSiteManagerApproved(isSiteManagerApproved);
		return req;
	}

	protected List<RequestMaterial> getSiteBeans() {
		List<RequestMaterial> materialRequestList = new ArrayList<>();
		materialRequestList.add(createRequestMaterialBean("OR0126", "Amala", "S004", "2018-08-03", "1", false, "1"));
		return materialRequestList;
	}

}
