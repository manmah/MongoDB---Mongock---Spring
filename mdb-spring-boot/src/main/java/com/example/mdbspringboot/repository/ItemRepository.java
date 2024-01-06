package com.example.mdbspringboot.repository;

import java.util.List;

import com.example.mdbspringboot.MdbSpringBootApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.model.GroceryItem;
import org.springframework.stereotype.Repository;

@Repository(MdbSpringBootApplication.CLIENTS_COLLECTION_NAME)
public interface ItemRepository extends MongoRepository<GroceryItem, String> {
	
	@Query("{name:'?0'}")
	GroceryItem findItemByName(String name);
	
	@Query(value="{category:'?0'}", fields="{'name' : 1, 'quantity' : 1}")
	List<GroceryItem> findAll(String category);
	
	public long count();

}
