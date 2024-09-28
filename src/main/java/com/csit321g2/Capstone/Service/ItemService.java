package com.csit321g2.Capstone.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csit321g2.Capstone.Entity.ItemEntity;
import com.csit321g2.Capstone.Entity.LogEntity;
import com.csit321g2.Capstone.Repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	ItemRepository irepo;

	public List<ItemEntity> getItemDash(){
		return irepo.getItemDash();
	}

	public List<Object> getStats2(){
        return irepo.getStats2();
    }
	public List<ItemEntity> getLogDash(){
		return irepo.getItemDash();
	}
	
	public ItemEntity insertItem(ItemEntity item) {
		item.setDeleted(false);
		return irepo.save(item);
	}
	
	public List<ItemEntity> getAllItems() {
		return irepo.findAll();
	}

	@SuppressWarnings("finally")
	public ItemEntity updateItem(Long propertyTag, ItemEntity newItemDetails) {
		ItemEntity item = new ItemEntity();
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

	public String deleteItem(Long propertyTag) {
		String msg = "";
		
		if (irepo.findById(propertyTag) != null) {
			// irepo.deleteById(propertyTag);
			ItemEntity test = irepo.findById(propertyTag).get();
			test.setDeleted(true);
			irepo.save(test);
			msg = "Item " + propertyTag + " is successfully deleted!";
		} else
			msg = "Item " + propertyTag + " does not exist.";
		return msg;
	}	

	public List<String>fetchAccPer(){
		return irepo.fetchAccPer();
	}

	public List<String>fetchDep(){
		return irepo.fetchDep();
	}

	public List<String>fetchDesig(){
		return irepo.fetchDesig();
	}

	public List<String>fetchStatus(){
		return irepo.fetchStatus();
	}

	public List<String>fetchUom(){
		return irepo.fetchUom();
	}

	public List<String>fetchSupp(){
		return irepo.fetchSupp();
	}

	public List<String>fetchBuilding(){
		return irepo.fetchBuilding();
	}

	public List<String>fetchRoom(){
		return irepo.fetchRoom();
	}

	public List<String>fetchName(){
		return irepo.fetchName();
	}

	public List<String>fetchModel(){
		return irepo.fetchModel();
	}

	public List<String>fetchType(){
		return irepo.fetchType();
	}

	public List<LocalDate>fetchInvoiceDate(){
		return irepo.fetchInvoiceDate();
	}

	public List<String>fetchLifespan(){
		return irepo.fetchLifespan();
	}

	public List<String>fetchLogsType(){
		return irepo.fetchLogsType();
	}

	public List<String>fetchLogsYear(){
		return irepo.fetchLogsYear();
	}

	public List<ItemEntity> fetchSearch(String search){
		return irepo.fetchSearch(search);
	}

	public ItemEntity fetchFullInfo(String info){
		return irepo.fetchFullInfo(info);
	}

	public int fetchQuantiLog(String num){
		return irepo.fetchQuantiLog(num);
	}

	public String fetchStatusLog(String type){
		return irepo.fetchStatusLog(type);
	}

	public List<ItemEntity>fetchItemByDepartment(String depa){
		return irepo.fetchItemByDepartment(depa);
	}

	@SuppressWarnings("finally")
	public ItemEntity requestItem(int number, long itemId){
		ItemEntity test = new ItemEntity();
		int quanti;
		int finalQuanti;
		float unitcost;
		float finalTotal;

		try{
			test = irepo.findById(itemId).get();

			quanti = test.getQuantity();
			unitcost = test.getUnitCost();

			finalQuanti = quanti - number;
			finalTotal = unitcost * (quanti - number);

			test.setQuantity(finalQuanti);
			test.setTotalCost(finalTotal);

			//test.setStatus(status);
		} catch (NoSuchElementException ex){
			throw new NoSuchElementException("Item " + itemId + " does not exist!");
		} finally {
			return irepo.save(test);
		}
		
	}
	
	@SuppressWarnings("finally")
	public ItemEntity updateStatus(Long iid, String status){
		ItemEntity test = new ItemEntity();
		try{
			test = irepo.findById(iid).get();

			test.setStatus(status);
		} catch (NoSuchElementException ex){
			throw new NoSuchElementException("Item " + iid + " does not exist!");
		} finally {
			return irepo.save(test);
		}
	}

	public List<LogEntity> logsSpeci(String num){
		return irepo.logsSpeci(num);
	}

	public List<LogEntity> searchLogs(String month, String year, String day, String type ,String bef, String aft){
		return irepo.searchLogs(month,year,day,type ,bef,aft);
	}

	public List<ItemEntity> fetchFilter(String acc_per,
	String department,
    String designation,
    String status,
    String uom,
    String supplier,
    String building,
    String room,
	String name,
    String model,
    String type,
	String invoice_date,
	String lifespan) {
		LocalDate localInvoiceDate = (invoice_date != null && !invoice_date.isEmpty()) 
		? LocalDate.parse(invoice_date) 
		: null;
		return irepo.fetchFilter(acc_per, department, designation, status, uom, supplier, building, room, name, model, type, localInvoiceDate, lifespan);
	}

	public Long fetchSum(String acc_per,
	String department,
    String designation,
    String status,
    String uom,
    String supplier,
    String building,
    String room,
	String name,
    String model,
    String type,
	String invoice_date,
	String lifespan) {
		LocalDate localInvoiceDate = (invoice_date != null && !invoice_date.isEmpty()) 
		? LocalDate.parse(invoice_date) 
		: null;
		return irepo.fetchSum(acc_per, department, designation, status, uom, supplier, building, room, name, model, type, localInvoiceDate, lifespan);
	}

}
