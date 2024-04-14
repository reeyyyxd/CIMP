package com.csit321g2.Capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/item")
public class ItemsController {
	
	@Autowired
	ItemsService iserv;
	
	@PostMapping("/insertItem")
	public ItemsEntity insertItem(@RequestBody ItemsEntity item) {
		return iserv.insertItem(item);
	}
	
	@GetMapping("/getAllItems")
	public List<ItemsEntity> getAllItems(){
		return iserv.getAllItems();
	}
	
	@PutMapping("/updateItem")
	public ItemsEntity updateItem(@RequestParam Long propertyTag, @RequestBody ItemsEntity newItemDetails) {
		return iserv.updateItem(propertyTag, newItemDetails);
	}
	
	@DeleteMapping("/deleteItem/{propertyTag}")
	public String deleteItem(@PathVariable Long propertyTag) {
		return iserv.deleteItem(propertyTag);
	}
}
