package com.csit321g2.Capstone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.csit321g2.Capstone.Entity.RequestEntity;

@Repository
public interface RequestRepository extends JpaRepository<RequestEntity, Long> {


    @Query(value="SELECT r FROM RequestEntity r WHERE r.status = 'pending'")
    public List<RequestEntity> getPending();

    @Query(value="SELECT r FROM RequestEntity r WHERE r.status = 'approved'")
    public List<RequestEntity> getApproved();

}
