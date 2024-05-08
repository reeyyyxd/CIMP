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


    @Query(value="SELECT i.accPerson FROM ItemsEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchAccPer();

    @Query(value="SELECT i.department FROM ItemsEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchDep();

    @Query(value="SELECT i.designation FROM ItemsEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchDesig();

    @Query(value="SELECT i.status FROM ItemsEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchStatus();

    @Query(value="SELECT i.unitOfMeasurement FROM ItemsEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchUom();

    @Query(value="SELECT i.supplier FROM ItemsEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchSupp();

    @Query(value="SELECT l.building FROM LocationEntity l, ItemsEntity i WHERE i.location.lid = l.lid AND (CAST(i.isDeleted AS int) = 0)")
    public List<String> fetchBuilding();

    @Query(value="SELECT l.room FROM LocationEntity l, ItemsEntity i WHERE i.location.lid = l.lid AND (CAST(i.isDeleted AS int) = 0)")
    public List<String> fetchRoom();

    @Query(value="SELECT d.name FROM DescriptionEntity d, ItemsEntity i WHERE i.description.did = d.did AND (CAST(i.isDeleted AS int) = 0)")
    public List<String> fetchName();

    @Query(value="SELECT d.model FROM DescriptionEntity d, ItemsEntity i WHERE i.description.did = d.did AND (CAST(i.isDeleted AS int) = 0)")
    public List<String> fetchModel();

    @Query(value="SELECT d.type FROM DescriptionEntity d, ItemsEntity i WHERE i.description.did = d.did AND (CAST(i.isDeleted AS int) = 0)")
    public List<String> fetchType();

    @Query(value="SELECT i.invoiceDate FROM ItemsEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchInvoiceDate();

    @Query(value="SELECT i.lifespan FROM ItemsEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchLifespan();

    @Query(value="SELECT l.type FROM LogEntity l")
    public List<String> fetchLogsType();

    @Query(value="SELECT CAST(FUNCTION('YEAR', l.date) AS String) FROM LogEntity l ORDER BY l.date ASC")
    public List<String> fetchLogsYear();

    
      
    @Query(value="UPDATE ItemsEntity i SET i.quantity = i.quantity - :number, i.totalCost = i.unitCost * (i.quantity - :number) WHERE i.iid = :itemId")
    @Modifying
    public int requestItem(@Param("number") int number, @Param("itemId") long itemId);


    @Query(value = "UPDATE ItemsEntity SET status = :stat WHERE iid = :statId")
    @Modifying
    public int updateStatus(@Param("stat")String stat, @Param("statId") int statId);

    @Query(value="SELECT l FROM LogEntity l WHERE CAST(l.item.iid AS STRING) LIKE %:num%")
    public List<LogEntity> logsSpeci(@Param("num") String num);
    

    @Query("SELECT l FROM LogEntity l WHERE " +
        "CAST(FUNCTION('MONTHNAME', l.date) AS string) LIKE %:month " +
        "AND CAST(FUNCTION('YEAR', l.date) AS string) LIKE %:year " +
        "AND CASE " +
            "    WHEN DAY(l.date) = 1 THEN '1' " +
            "    WHEN DAY(l.date) = 2 THEN '2' " +
            "    WHEN DAY(l.date) = 3 THEN '3' " +
            "    WHEN DAY(l.date) = 4 THEN '4' " +
            "    WHEN DAY(l.date) = 5 THEN '5' " +
            "    WHEN DAY(l.date) = 6 THEN '6' " +
            "    WHEN DAY(l.date) = 7 THEN '7' " +
            "    WHEN DAY(l.date) = 8 THEN '8' " +
            "    WHEN DAY(l.date) = 9 THEN '9' " +
            "    WHEN DAY(l.date) = 10 THEN '10' " +
            "    WHEN DAY(l.date) = 11 THEN 'eleven' " +
            "    WHEN DAY(l.date) = 12 THEN 'twelve' " +
            "    WHEN DAY(l.date) = 13 THEN 'thirteen' " +
            "    WHEN DAY(l.date) = 14 THEN 'fourteen' " +
            "    WHEN DAY(l.date) = 15 THEN 'fifteen' " +
            "    WHEN DAY(l.date) = 16 THEN 'sixteen' " +
            "    WHEN DAY(l.date) = 17 THEN 'seventeen' " +
            "    WHEN DAY(l.date) = 18 THEN 'eighteen' " +
            "    WHEN DAY(l.date) = 19 THEN 'nineteen' " +
            "    WHEN DAY(l.date) = 20 THEN 'twenties' " +
            "    WHEN DAY(l.date) = 21 THEN 'twenty one' " +
            "    WHEN DAY(l.date) = 22 THEN 'twenty two' " +
            "    WHEN DAY(l.date) = 23 THEN 'twenty three' " +
            "    WHEN DAY(l.date) = 24 THEN 'twenty four' " +
            "    WHEN DAY(l.date) = 25 THEN 'twenty five' " +
            "    WHEN DAY(l.date) = 26 THEN 'twenty six' " +
            "    WHEN DAY(l.date) = 27 THEN 'twenty seven' " +
            "    WHEN DAY(l.date) = 28 THEN 'twenty eight' " +
            "    WHEN DAY(l.date) = 29 THEN 'twenty nine' " +
            "    WHEN DAY(l.date) = 30 THEN 'thirties' " +
            "    WHEN DAY(l.date) = 31 THEN 'thirty one' " +
            "    ELSE 'unknown' " +
            "END LIKE  :day% " +
        "AND l.type LIKE %:type% " +
        "AND ((:bef = '' AND :aft = '') OR (CAST(l.time AS STRING) BETWEEN :bef and :aft))")
    List<LogEntity> searchLogs(
        @Param("month") String month,
        @Param("year") String year,
        @Param("day") String day,
        @Param("type") String type ,
        @Param("bef") String bef,
        @Param("aft") String aft);


    


    @Query(value="SELECT i,l,d FROM ItemsEntity i , LocationEntity l, DescriptionEntity d " +
    "WHERE i.location.lid = l.lid AND i.description.did = d.did " +
    "AND (CAST(i.iid AS STRING) LIKE %:search% " +  
    "OR CAST(i.invoiceNumber AS STRING) LIKE %:search% " +
    "OR CAST(i.issueOrder AS STRING) LIKE %:search% " +
    "OR d.serialNumber LIKE %:search%) AND CAST(i.isDeleted AS int) = 0")
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
    "AND CAST(i.lifespan AS STRING) LIKE %:lifespan% ) AND CAST(i.isDeleted AS int) = 0")
public List<ItemsEntity> fetchFilter(@Param("acc_per") String acc_per,
                                      @Param("department") String department,
                                      @Param("designation") String designation,
                                      @Param("status") String status,
                                      @Param("uom") String uom,
                                      @Param("supplier") String supplier,
                                      @Param("building") String building,
                                      @Param("room") String room,
                                      @Param("name") String name,
                                      @Param("model") String model,
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
    "AND CAST(i.lifespan AS STRING) LIKE %:lifespan% ) AND CAST(i.isDeleted AS int) = 0")
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
