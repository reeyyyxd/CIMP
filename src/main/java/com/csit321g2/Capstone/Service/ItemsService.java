package com.csit321g2.Capstone.Service;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csit321g2.Capstone.Entity.ItemsEntity;
import com.csit321g2.Capstone.Repository.ItemsRepository;

@Service
public class ItemsService {
	
	@Autowired
	ItemsRepository srepo;
	
	public ItemsEntity insertItem(ItemsEntity item) {
		return srepo.save(item);
	}
	
	public List<ItemsEntity> getAllItems(){
		return srepo.findAll();
	}
	
	@SuppressWarnings("finally")
	public ItemsEntity updateItem(int propertyTag, ItemsEntity newItemDetails) {
		ItemsEntity item = new ItemsEntity();
		try {
			//search id b4 update
			item = srepo.findById(propertyTag).get();
			
			//update
			item.setIssueOrder(newItemDetails.getIssueOrder());
			item.setDepartment(newItemDetails.getDepartment());
			item.setAccountablePerson(newItemDetails.getAccountablePerson());
			item.setDesignation(newItemDetails.getDesignation());
			item.setInvoiceNumber(newItemDetails.getInvoiceNumber());
			item.setYearPurchased(newItemDetails.getYearPurchased());
			item.setSupplier(newItemDetails.getSupplier());
			item.setQuantity(newItemDetails.getQuantity());
			item.setUom(newItemDetails.getUom());
			item.setDescription(newItemDetails.getDescription());
			item.setUnitCost(newItemDetails.getUnitCost());
			item.setTotalCost(newItemDetails.getTotalCost());
			item.setInventoryLocation(newItemDetails.getInventoryLocation());
			item.setLifespan(newItemDetails.getLifespan());
			item.setStatus(newItemDetails.getStatus());
			item.setRemarks(newItemDetails.getRemarks());
			
		} catch (NoSuchElementException ex){
			throw new NoSuchElementException("Item " + propertyTag + " does not exist!");
		} finally {
			return srepo.save(item);
		}
	}
	
	public String deleteItem(int propertyTag) {
		String msg = "";
		
		if (srepo.findById(propertyTag) != null) {
			srepo.deleteById(propertyTag);
			msg = "Item " + propertyTag + " is successfully deleted!";
		} else
			msg = "Item " + propertyTag + " does not exist.";
		return msg;
	}	
}
