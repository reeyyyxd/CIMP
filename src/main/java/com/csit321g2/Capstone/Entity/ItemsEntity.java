package com.csit321g2.Capstone.Entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblitems")
public class ItemsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "propertyTag")
	private long iid;
	
	@Column(name = "issueOrderNumber")
	private int issueOrder;
	
	private String department;
	
	@Column(name = "accountablePerson")
	private String accPerson;
	
	private String designation;
	
	private String invoiceNumber;
	
	private LocalDate invoiceDate;
	
	private String supplier;
	
	private int quantity;
	
	@Column(name = "uom")
	private String unitOfMeasurement;
	
	private String description;
	
	private float unitCost;
	
	private float totalCost;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "location_id", referencedColumnName = "lid")
	private LocationEntity location;

	private String status;

	private String lifespan;
	
	private String remarks;

	public ItemsEntity() {
	}

	public ItemsEntity(int issueOrder, String department, String accPerson, String designation, String invoiceNumber,
			LocalDate invoiceDate, String supplier, int quantity, String unitOfMeasurement, String description,
			float unitCost, float totalCost, LocationEntity location, String status, String lifespan, String remarks) {
		this.issueOrder = issueOrder;
		this.department = department;
		this.accPerson = accPerson;
		this.designation = designation;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
		this.supplier = supplier;
		this.quantity = quantity;
		this.unitOfMeasurement = unitOfMeasurement;
		this.description = description;
		this.unitCost = unitCost;
		this.totalCost = totalCost;
		this.location = location;
		this.status = status;
		this.lifespan = lifespan;
		this.remarks = remarks;
	}

	public long getIid() {
		return iid;
	}

	public void setIid(long iid) {
		this.iid = iid;
	}

	public int getIssueOrder() {
		return issueOrder;
	}

	public void setIssueOrder(int issueOrder) {
		this.issueOrder = issueOrder;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getAccPerson() {
		return accPerson;
	}

	public void setAccPerson(String accPerson) {
		this.accPerson = accPerson;
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

	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
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

	public String getUnitOfMeasurement() {
		return unitOfMeasurement;
	}

	public void setUnitOfMeasurement(String unitOfMeasurement) {
		this.unitOfMeasurement = unitOfMeasurement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(float unitCost) {
		this.unitCost = unitCost;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public LocationEntity getLocation() {
		return location;
	}

	public void setLocation(LocationEntity location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLifespan() {
		return lifespan;
	}

	public void setLifespan(String lifespan) {
		this.lifespan = lifespan;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
