package com.csit321g2.Capstone.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblitems")
public class ItemsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int propertyTag;
	
	private String issueOrder;
	
	private String department;
	
	private String accountablePerson;
	
	private String designation;
	
	private String invoiceNumber;
	
	private int yearPurchased;
	
	private String supplier;
	
	private int quantity;
	
	private String uom;
	
	private String description;
	
	private int unitCost;
	
	private int totalCost;
	
	private String inventoryLocation;
	
	private String lifespan;
	
	private String status;
	
	private String remarks;

	public ItemsEntity() {
		super();
	}

	public ItemsEntity(int propertyTag, String issueOrder, String department, String accountablePerson,
						 String designation, String invoiceNumber, int yearPurchased,String supplier, 
						 int quantity, String uom, String description, int unitCost,
						 int totalCost, String inventoryLocation, String lifespan, String status, String remarks) {
		super();
		this.propertyTag = propertyTag;
		this.issueOrder = issueOrder;
		this.department = department;
		this.accountablePerson = accountablePerson;
		this.designation = designation;
		this.invoiceNumber = invoiceNumber;
		this.yearPurchased = yearPurchased;
		this.supplier = supplier;
		this.totalCost = totalCost;
		this.inventoryLocation = inventoryLocation;
		this.lifespan = lifespan;
		this.status = status;
		this.remarks = remarks;
	}

	public int getPropertyTag() {
		return propertyTag;
	}

	public void setPropertyTag(int propertyTag) {
		this.propertyTag = propertyTag;
	}

	public String getIssueOrder() {
		return issueOrder;
	}

	public void setIssueOrder(String issueOrder) {
		this.issueOrder = issueOrder;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAccountablePerson() {
		return accountablePerson;
	}

	public void setAccountablePerson(String accountablePerson) {
		this.accountablePerson = accountablePerson;
	}
	
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public int getYearPurchased() {
		return yearPurchased;
	}

	public void setYearPurchased(int yearPurchased) {
		this.yearPurchased = yearPurchased;
	}
	
	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getUom() {
		return uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(int unitCost) {
		this.unitCost = unitCost;
	}
	
	public int getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	
	public String getInventoryLocation() {
		return inventoryLocation;
	}

	public void setInventoryLocation(String inventoryLocation) {
		this.inventoryLocation = inventoryLocation;
	}
	
	public String getLifespan() {
		return lifespan;
	}

	public void setLifespan(String lifespan) {
		this.lifespan = lifespan;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
