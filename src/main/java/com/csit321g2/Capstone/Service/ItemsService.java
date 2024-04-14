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
	ItemsRepository irepo;
	
	public ItemsEntity insertItem(ItemsEntity item) {
		return irepo.save(item);
	}
	
	public List<ItemsEntity> getAllItems(){
		return irepo.findAll();
	}
	
	@SuppressWarnings("finally")
	public ItemsEntity updateItem(Long propertyTag, ItemsEntity newItemDetails) {
		ItemsEntity item = new ItemsEntity();
		try {
			//search id b4 update
			item = irepo.findById(propertyTag).get();
			
			//update
			item.setIssueOrder(newItemDetails.getIssueOrder());
			item.setDepartment(newItemDetails.getDepartment());
			item.setAccPerson(newItemDetails.getAccPerson());
			item.setDesignation(newItemDetails.getDesignation());
			item.setInvoiceNumber(newItemDetails.getInvoiceNumber());
			item.setInvoiceDate(newItemDetails.getInvoiceDate());
			item.setSupplier(newItemDetails.getSupplier());
			item.setQuantity(newItemDetails.getQuantity());
			item.setUnitOfMeasurement(newItemDetails.getUnitOfMeasurement());
			item.setDescription(newItemDetails.getDescription());
			item.setUnitCost(newItemDetails.getUnitCost());
			item.setTotalCost(newItemDetails.getTotalCost());
			item.setLocation(newItemDetails.getLocation());
			item.setLifespan(newItemDetails.getLifespan());
			item.setStatus(newItemDetails.getStatus());
			item.setRemarks(newItemDetails.getRemarks());
			
		} catch (NoSuchElementException ex){
			throw new NoSuchElementException("Item " + propertyTag + " does not exist!");
		} finally {
			return irepo.save(item);
		}
	}
	
	public String deleteItem(int propertyTag) {
		String msg = "";
		
		if (irepo.findById(propertyTag) != null) {
			irepo.deleteById(propertyTag);
			msg = "Item " + propertyTag + " is successfully deleted!";
		} else
			msg = "Item " + propertyTag + " does not exist.";
		return msg;
	}	
}
