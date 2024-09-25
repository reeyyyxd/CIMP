package com.csit321g2.Capstone.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csit321g2.Capstone.Entity.ItemEntity;
import com.csit321g2.Capstone.Entity.LogEntity;


@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>{
	
    //@Query(value="SELECT s FROM AccountEntity s WHERE firstname LIKE %:fname% AND lastname LIKE %:lname%")
	//public List<AccountEntity> fetchAllCustom(@Param("fname")String fname,@Param("lname")String lname);


    @Query(value="SELECT i FROM ItemEntity i WHERE CAST(i.isDeleted AS int) = 0 ORDER BY i.iid DESC LIMIT 10")
    public List<ItemEntity> getItemDash();

    @Query(value="SELECT l FROM LogEntity l, ItemEntity i WHERE l.logid = i.iid AND CAST(i.isDeleted AS int) = 0 ORDER BY l.logid DESC LIMIT 10")
    public List<ItemEntity> getLogDash();

    @Query(value="SELECT i.accPerson FROM ItemEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchAccPer();

    @Query(value="SELECT i.department FROM ItemEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchDep();

    @Query(value="SELECT i.designation FROM ItemEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchDesig();

    @Query(value="SELECT i.status FROM ItemEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchStatus();

    @Query(value="SELECT i.unitOfMeasurement FROM ItemEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchUom();

    @Query(value="SELECT i.supplier FROM ItemEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchSupp();

    @Query(value="SELECT l.building FROM LocationEntity l, ItemEntity i WHERE i.location.lid = l.lid AND (CAST(i.isDeleted AS int) = 0)")
    public List<String> fetchBuilding();

    @Query(value="SELECT l.room FROM LocationEntity l, ItemEntity i WHERE i.location.lid = l.lid AND (CAST(i.isDeleted AS int) = 0)")
    public List<String> fetchRoom();

    @Query(value="SELECT d.name FROM DescriptionEntity d, ItemEntity i WHERE i.description.did = d.did AND (CAST(i.isDeleted AS int) = 0)")
    public List<String> fetchName();

    @Query(value="SELECT d.model FROM DescriptionEntity d, ItemEntity i WHERE i.description.did = d.did AND (CAST(i.isDeleted AS int) = 0)")
    public List<String> fetchModel();

    @Query(value="SELECT d.type FROM DescriptionEntity d, ItemEntity i WHERE i.description.did = d.did AND (CAST(i.isDeleted AS int) = 0)")
    public List<String> fetchType();

    @Query(value="SELECT i.invoiceDate FROM ItemEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<LocalDate> fetchInvoiceDate();

    @Query(value="SELECT i.lifespan FROM ItemEntity i WHERE CAST(i.isDeleted AS int) = 0")
    public List<String> fetchLifespan();

    @Query(value="SELECT l.type FROM LogEntity l")
    public List<String> fetchLogsType();

    @Query(value="SELECT CAST(FUNCTION('YEAR', l.date) AS String) FROM LogEntity l ORDER BY l.date ASC")
    public List<String> fetchLogsYear();

    @Query(value="SELECT i.quantity FROM ItemEntity i WHERE i.iid = :num")
    public int fetchQuantiLog(@Param("num") String num);

    @Query(value="SELECT i.status FROM ItemEntity i WHERE i.iid = :type")
    public String fetchStatusLog(@Param("type") String type);

    @Query(value="SELECT i FROM ItemEntity i WHERE CAST(i.isDeleted AS int) = 0 AND i.department = :depa")
    public List<ItemEntity> fetchItemByDepartment(@Param("depa") String depa);
    
      
    @Query(value="UPDATE ItemEntity i SET i.quantity = i.quantity - :number, i.totalCost = i.unitCost * (i.quantity - :number) WHERE i.iid = :itemId")
    @Modifying
    public int requestItem(@Param("number") int number, @Param("itemId") long itemId);


    @Query(value = "UPDATE ItemEntity SET status = :stat WHERE iid = :statId")
    @Modifying
    public int updateStatus(@Param("stat")String stat, @Param("statId") int statId);

    @Query(value="SELECT l FROM LogEntity l WHERE CAST(l.item.iid AS STRING) LIKE %:num%")
    public List<LogEntity> logsSpeci(@Param("num") String num);
    

    @Query("SELECT l FROM LogEntity l WHERE " +
        "CASE " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'January' THEN '1' " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'February' THEN '2' " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'March' THEN '3' " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'April' THEN '4' " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'May' THEN '5' " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'June' THEN '6' " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'July' THEN '7' " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'August' THEN '8' " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'September' THEN '9' " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'October' THEN 'ten' " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'November' THEN 'eleven' " +
        "    WHEN FUNCTION('MONTHNAME', l.date) = 'December' THEN 'twelve' " +
        "    ELSE 'unknown' " +
        "END LIKE :month% " +
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
            "END LIKE :day% " +
        "AND l.type LIKE %:type% " +
        "AND ((:bef = '' AND :aft = '') OR (CAST(l.time AS STRING) BETWEEN :bef and :aft))")
List<LogEntity> searchLogs(
    @Param("month") String month,
    @Param("year") String year,
    @Param("day") String day,
    @Param("type") String type ,
    @Param("bef") String bef,
    @Param("aft") String aft);



    


    @Query(value="SELECT i,l,d FROM ItemEntity i , LocationEntity l, DescriptionEntity d " +
    "WHERE i.location.lid = l.lid AND i.description.did = d.did " +
    "AND (CAST(i.iid AS STRING) LIKE %:search% " +  
    "OR CAST(i.invoiceNumber AS STRING) LIKE %:search% " +
    "OR CAST(i.issueOrder AS STRING) LIKE %:search% " +
    "OR d.serialNumber LIKE %:search%) AND CAST(i.isDeleted AS int) = 0")
    public List<ItemEntity> fetchSearch(@Param("search") String search);

    @Query(value="SELECT i,l,d FROM ItemEntity i , LocationEntity l, DescriptionEntity d " +
    "WHERE i.location.lid = l.lid AND i.description.did = d.did " +
    "AND (CAST(i.iid AS STRING) = %:info%)")
    public ItemEntity fetchFullInfo(@Param("info") String info);

    @Query(value="SELECT i,l,d FROM ItemEntity i , LocationEntity l, DescriptionEntity d " +
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
    "AND i.invoiceDate = :invoice_date " + // no need for casting
    "AND CAST(i.lifespan AS STRING) LIKE %:lifespan% ) AND CAST(i.isDeleted AS int) = 0")
    public List<ItemEntity> fetchFilter(@Param("acc_per") String acc_per,
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
                                          @Param("invoice_date") LocalDate invoice_date, // keep this as LocalDate
                                          @Param("lifespan") String lifespan);
    


    @Query(value="SELECT SUM(totalCost) FROM ItemEntity i , LocationEntity l, DescriptionEntity d " +
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
    @Param("invoice_date") LocalDate invoice_date,
    @Param("lifespan") String lifespan);
	
}
