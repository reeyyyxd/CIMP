package com.csit321g2.Capstone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csit321g2.Capstone.Entity.ItemsEntity;
import com.csit321g2.Capstone.Entity.LogEntity;


@Repository
public interface ItemsRepository extends JpaRepository<ItemsEntity, Long>{
	
    //@Query(value="SELECT s FROM AccountEntity s WHERE firstname LIKE %:fname% AND lastname LIKE %:lname%")
	//public List<AccountEntity> fetchAllCustom(@Param("fname")String fname,@Param("lname")String lname);


    @Query(value="SELECT i.accPerson FROM ItemsEntity i")
    public List<String> fetchAccPer();

    @Query(value="SELECT i.department FROM ItemsEntity i")
    public List<String> fetchDep();

    @Query(value="SELECT i.designation FROM ItemsEntity i")
    public List<String> fetchDesig();

    @Query(value="SELECT i.status FROM ItemsEntity i")
    public List<String> fetchStatus();

    @Query(value="SELECT i.unitOfMeasurement FROM ItemsEntity i")
    public List<String> fetchUom();

    @Query(value="SELECT i.supplier FROM ItemsEntity i")
    public List<String> fetchSupp();

    @Query(value="SELECT l.building FROM LocationEntity l")
    public List<String> fetchBuilding();

    @Query(value="SELECT l.room FROM LocationEntity l")
    public List<String> fetchRoom();

    @Query(value="SELECT d.name FROM DescriptionEntity d")
    public List<String> fetchName();

    @Query(value="SELECT d.model FROM DescriptionEntity d")
    public List<String> fetchModel();

    @Query(value="SELECT d.type FROM DescriptionEntity d")
    public List<String> fetchType();

    @Query(value="SELECT i.invoiceDate FROM ItemsEntity i")
    public List<String> fetchInvoiceDate();

    @Query(value="SELECT i.lifespan FROM ItemsEntity i")
    public List<String> fetchLifespan();
    
      
    @Query(value="UPDATE ItemsEntity i \n " + 
                "SET i.quantity = i.quantity - :number, " + 
                "i.totalCost = i.unitCost * (i.quantity - :number) \n " +
                "WHERE i.iid = :itemId")
    @Modifying
    public int requestItem(@Param("number") int number, @Param("itemId") long itemId);


    @Query(value = "UPDATE ItemsEntity SET status = :stat WHERE iid = :statId")
    @Modifying
    public int updateStatus(@Param("stat")String stat, @Param("statId") int statId);


    

    @Query("SELECT l FROM LogEntity l WHERE " +
        "CAST(FUNCTION('MONTH', l.date) AS string) LIKE %:month% " +
        "AND CAST(FUNCTION('YEAR', l.date) AS string) LIKE %:year% " +
        "AND CAST(FUNCTION('DAY', l.date) AS string) LIKE %:day% " +
        "AND l.type LIKE %:type% ")
        //"AND FUNCTION('FORMAT', l.time, 'HH:mm:ss') BETWEEN %:bef% AND %:aft%")
List<LogEntity> searchLogs(
        @Param("month") String month,
        @Param("year") String year,
        @Param("day") String day,
        @Param("type") String type
        /*@Param("bef") String bef,
        @Param("aft") String aft*/);


    



    @Query(value="SELECT i,l,d FROM ItemsEntity i , LocationEntity l, DescriptionEntity d " +
    "WHERE i.location.lid = l.lid AND i.description.did = d.did " +
    "AND (CAST(i.iid AS STRING) LIKE %:search% " +  
    "OR CAST(i.invoiceNumber AS STRING) LIKE %:search% " +
    "OR CAST(i.issueOrder AS STRING) LIKE %:search% " +
    "OR d.serialNumber LIKE %:search%)")
    public List<ItemsEntity> fetchSearch(@Param("search") String search);

    @Query(value="SELECT i,l,d FROM ItemsEntity i , LocationEntity l, DescriptionEntity d " +
    "WHERE i.location.lid = l.lid AND i.description.did = d.did " +
    "AND (CAST(i.iid AS STRING) = %:info%)")
    public ItemsEntity fetchFullInfo(@Param("info") String info);

    @Query(value="SELECT i,l,d FROM ItemsEntity i , LocationEntity l, DescriptionEntity d " +
    "WHERE i.location.lid = l.lid AND i.description.did = d.did " +
    "AND (i.accPerson LIKE %:acc_per% " +
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
    "AND CAST(i.lifespan AS STRING) LIKE %:lifespan% )")
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

    @Query(value="SELECT SUM(totalCost) FROM ItemsEntity i , LocationEntity l, DescriptionEntity d " +
    "WHERE i.location.lid = l.lid AND i.description.did = d.did " +
    "AND (i.accPerson LIKE %:acc_per% " +
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
    "AND CAST(i.lifespan AS STRING) LIKE %:lifespan% )")
    public long fetchSum(@Param("acc_per")String acc_per,
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
