package sliit.g01.procurementg01.api.service;

import java.util.List;

import sliit.g01.procurementg01.api.model.Item;

/**
 * @author Tharindu
 **/
public interface ItemService {

	Boolean addItem(Item item);

	List<Item> getAllItems();

	Item getItem(String itemId);

	Boolean deleteItem(String itemId);

	Boolean addToRestrictedList(String itemId);

}
