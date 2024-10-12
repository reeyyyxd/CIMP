package com.csit321g2.Capstone.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csit321g2.Capstone.Entity.ItemEntity;
import com.csit321g2.Capstone.Entity.RequestEntity;
import com.csit321g2.Capstone.Repository.ItemRepository;
import com.csit321g2.Capstone.Repository.RequestRepository;

import jakarta.transaction.Transactional;

@Service
public class RequestService {

    @Autowired
    RequestRepository rrepo;

    @Autowired
    ItemRepository irepo;

    public List<RequestEntity> getPending(){
        return rrepo.getPending();
    }

    public List<RequestEntity> getAllItems(){
        return rrepo.findAll();
    }

    public List<Object> getStats(){
        return rrepo.getStats();
    }

    public List<RequestEntity> getApproved(){
        return rrepo.getApproved();
    }

    public RequestEntity addRequest(Long iid) {
        RequestEntity request = new RequestEntity();
        Optional<ItemEntity> optionalItemEntity = irepo.findById(iid);

        if(optionalItemEntity.isPresent()) {
            request.setItem(optionalItemEntity.get());
        }

        request.setDate_req(LocalDateTime.now());
        // request.setQuantity(quantity);
        request.setStatus("pending");
        return rrepo.save(request);
    }

    public RequestEntity updateStatus(Long rid, String status) {
        RequestEntity dummy = new RequestEntity();
        ItemEntity dummy2 = new ItemEntity();
        try {
            dummy = rrepo.findById(rid).get();
            dummy2 = irepo.findById(dummy.getItem().getIid()).get();

            dummy.setStatus(status);
            dummy.setDate_app(LocalDateTime.now());

            String dummyStatus = new String("");
            if(status.equals("rejected")) {
                dummyStatus = "TO BE ASSIGNED";
            } else if(status.equals("approved")) {
                dummyStatus = "ASSIGNED";
            } else if(status.equals("pending return")) {
                dummyStatus = "TO BE RETURNED";
            } else if(status.equals("approved return")) {
                dummyStatus = "TO BE ASSIGNED";

                dummy.setDate_app(null);
                dummy2.setAccPerson(null);
                dummy2.setDesignation("");
                dummy2.setDepartment("");
            }

            dummy2.setStatus(dummyStatus);

            // dummy2.setQuantity(dummy2.getQuantity() - dummy.getQuantity());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Request " + rid + " does not exist!");
        } 

        return rrepo.save(dummy);
    }

    public List<RequestEntity> getRequestsByUser(Long uid) {
        return rrepo.findByItem_AccPerson_Uid(uid);
    }

    public List<RequestEntity> getRequestsByItemStatus(String status) {
        return rrepo.findRequestsByItemStatus(status);
    }

}
