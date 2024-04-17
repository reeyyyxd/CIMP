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
