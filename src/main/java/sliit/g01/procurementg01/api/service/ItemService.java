package sliit.g01.procurementg01.api.service;

import java.util.List;

import sliit.g01.procurementg01.api.model.Item;

/**
 * @author Tharindu
 * @author Tharushi
 **/
public interface ItemService {

	Boolean addItem(Item item);

	List<Item> getAllItems();

	List<Item> getItemsSuppliedBy(String supplierId);

	Item getItem(String itemId);

	Boolean deleteItem(String itemId);

	Boolean addToRestrictedList(String itemId);

}
