package sliit.g01.procurementg01.api.service;

import java.util.List;
import java.util.Map;

import sliit.g01.procurementg01.api.model.Item;

/**
 * @author Tharindu
 * @author Tharushi
 **/
public interface ItemService {

	Boolean addItem(Item item);

	List<Item> getAllItems();

	List<Item> getItemsSuppliedBy(String supplierId);

	Map<String, List<Item>> getAllItemsGroupedBySupplier();

	Item getItem(String itemId);

	Boolean updateItem(Item item);

	Boolean deleteItem(String itemId);

	Boolean addToRestrictedList(String itemId);

}
