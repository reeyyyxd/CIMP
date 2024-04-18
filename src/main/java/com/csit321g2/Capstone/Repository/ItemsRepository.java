package com.csit321g2.Capstone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csit321g2.Capstone.Entity.ItemsEntity;

@Repository
public interface ItemsRepository extends JpaRepository<ItemsEntity, Long>{
	
    //@Query(value="SELECT s FROM AccountEntity s WHERE firstname LIKE %:fname% AND lastname LIKE %:lname%")
	//public List<AccountEntity> fetchAllCustom(@Param("fname")String fname,@Param("lname")String lname);


    @Query(value="SELECT i.accPerson FROM ItemsEntity i")
    public List<String> fetchAccPer();

    @Query(value="SELECT i.department FROM ItemsEntity i")
    public List<String> fetchDep();

    @Query("SELECT i.designation FROM ItemsEntity i")
    public List<String> fetchDesig();

    @Query(value="SELECT i.status FROM ItemsEntity i")
    public List<String> fetchStatus();

    @Query(value="SELECT i.unitOfMeasurement FROM ItemsEntity i")
    public List<String> fetchUom();

    @Query("SELECT i.supplier FROM ItemsEntity i")
    public List<String> fetchSupp();

    @Query(value="SELECT l.building FROM LocationEntity l")
    public List<String> fetchBuilding();

    @Query(value="SELECT l.room FROM LocationEntity l")
    public List<String> fetchRoom();

    @Query("SELECT d.name FROM DescriptionEntity d")
    public List<String> fetchName();

    @Query(value="SELECT d.model FROM DescriptionEntity d")
    public List<String> fetchModel();

    @Query(value="SELECT d.type FROM DescriptionEntity d")
    public List<String> fetchType();

    @Query("SELECT i.invoiceDate FROM ItemsEntity i")
    public List<String> fetchInvoiceDate();

    @Query("SELECT i.lifespan FROM ItemsEntity i")
    public List<String> fetchLifespan();

    @Query(value="SELECT i,l,d FROM ItemsEntity i , LocationEntity l, DescriptionEntity d " +
    "WHERE i.location.lid = l.lid AND i.description.did = d.did " +
    "AND i.accPerson LIKE %:acc_per% " +
    "AND i.department LIKE %:department% " +
    "AND i.designation LIKE %:designation% " +
    "AND i.status LIKE %:status% " +
    "AND i.unitOfMeasurement LIKE %:uom% " +
    "AND i.supplier LIKE %:supplier% " +
    "AND l.building LIKE %:building% " +
    "AND l.room LIKE %:room% " +
    "AND d.name LIKE %:name% " +
    "AND d.model LIKE %:model% " +
    "AND d.type LIKE %:type% " +
    "AND CAST(i.invoiceDate AS STRING) LIKE %:invoice_date% " +
    "AND CAST(i.lifespan AS STRING) LIKE %:lifespan% ")
    public List<ItemsEntity> fetchFilter(@Param("acc_per")String acc_per,
    @Param("department")String department,
    @Param("designation")String designation,
    @Param("status")String status,
    @Param("uom")String uom,
    @Param("supplier")String supplier,
    @Param("building")String building,
    @Param("room")String room,
    @Param("name")String name,
    @Param("model")String model,
    @Param("type") String type,
    @Param("invoice_date") String invoice_date,
    @Param("lifespan") String lifespan);
	
}
