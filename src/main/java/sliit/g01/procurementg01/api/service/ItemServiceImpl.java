package sliit.g01.procurementg01.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sliit.g01.procurementg01.api.model.Item;
import sliit.g01.procurementg01.api.repository.ItemRepository;

@Service("itemService")
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepository itemRepository;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean addToRestrictedList(String itemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
