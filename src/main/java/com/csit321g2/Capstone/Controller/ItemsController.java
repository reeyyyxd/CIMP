package com.csit321g2.Capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.Capstone.Entity.ItemsEntity;
import com.csit321g2.Capstone.Service.ItemsService;

@RestController
@RequestMapping("/item")
public class ItemsController {
	
	@Autowired
	ItemsService sserv;
	
	@GetMapping("/print")
	public String printHello() {
		return "Hello, Ellan Estandarte!";
	}
	
	@PostMapping("/insertItem")
	public ItemsEntity insertItem(@RequestBody ItemsEntity item) {
		return sserv.insertItem(item);
	}
	
	@GetMapping("/getAllItems")
	public List<ItemsEntity> getAllItems(){
		return sserv.getAllItems();
	}
	
	@PutMapping("/updateItem")
	public ItemsEntity updateItem(@RequestParam int propertyTag, @RequestBody ItemsEntity newItemDetails) {
		return sserv.updateItem(propertyTag, newItemDetails);
	}
	
	@DeleteMapping("/deleteItem/{propertyTag}")
	public String deleteItem(@PathVariable int propertyTag) {
		return sserv.deleteItem(propertyTag);
	}
}
