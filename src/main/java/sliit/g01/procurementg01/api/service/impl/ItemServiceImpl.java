package sliit.g01.procurementg01.api.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.model.Supplier;
import sliit.g01.procurementg01.api.repository.ItemRepository;
import sliit.g01.procurementg01.api.service.ItemService;

/**
 * @author Tharindu
 **/
@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

    @Autowired
    private SupplierServiceImpl supplierService;


    @Override
	public Boolean addItem(Item item) {
		return (itemRepository.save(item) != null);
	}

	@Override
	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	@Override
	public Item getItem(String itemId) {
		return itemRepository.findByItemId(itemId);
	}

	@Override
	public Boolean deleteItem(String itemId) {
		Item existingItem = itemRepository.findByItemId(itemId);

		if (existingItem != null) {
			System.out.println(existingItem.getItemName());
			itemRepository.delete(existingItem);
			return true;
		}

		return false;
	}

	@Override
	public Boolean addToRestrictedList(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	// this will make a list of items offered by the given supplier.
	@Override
	public List<Item> getItemsSuppliedBy(String supplierId) {
		return itemRepository.findItemsBySupplierId(supplierId);
	}

	// items, grouped by the supplier.
	@Override
	public Map<String, List<Item>> getAllItemsGroupedBySupplier() {
		Map<String, List<Item>> itemsOfferedBySupplier = new HashMap<>();
		List<Supplier> suppliers = supplierService.getAllSuppliers();

		// we go through each supplier and get items offered by them.
        for (Supplier s: suppliers) {
            List<Item> items = itemRepository.findItemsBySupplierId(s.getSupplierId());
            itemsOfferedBySupplier.put(s.getSupplierId(), items);
        }

        return itemsOfferedBySupplier;
	}

	@Override
	public Boolean updateItem(Item item) {
		// check if an item under the given id exist.
		Item existingItem = itemRepository.findByItemId(item.getItemId());

		if (item != null) {
			itemRepository.save(item);	// since the id is the same, the existing record will be replaced.
			return true;
		}

		return false;
	}
}
