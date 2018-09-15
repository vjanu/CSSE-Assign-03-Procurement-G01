package sliit.g01.procurementg01.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import sliit.g01.procurementg01.api.model.Item;

public interface ItemRepository extends MongoRepository<Item, String> {
	Item findByItemId(String itemId);
}
