package com.csit321g2.Capstone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csit321g2.Capstone.Entity.RequestEntity;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Long> {

    List<RequestEntity> findByItem_AccPerson_Uid(Long uid);

    @Query("SELECT r FROM RequestEntity r JOIN r.item i WHERE i.status = :itemStatus")
    List<RequestEntity> findRequestsByItemStatus(@Param("itemStatus") String itemStatus);
    
    @Query(value="SELECT r FROM RequestEntity r WHERE r.status = 'pending'")
    public List<RequestEntity> getPending();

    @Query(value="SELECT r FROM RequestEntity r WHERE r.status = 'approved'")
    public List<RequestEntity> getApproved();

    @Query(value="SELECT YEAR(r.date_app), MONTH(r.date_app), DATEDIFF(r.date_app, r.date_req) FROM RequestEntity r")
    public List<Object> getStats();



}
