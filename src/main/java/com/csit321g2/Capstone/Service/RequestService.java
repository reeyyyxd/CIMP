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

    public RequestEntity addRequest(Long iid, int quantity) {
        RequestEntity request = new RequestEntity();
        Optional<ItemEntity> optionalItemEntity = irepo.findById(iid);

        if(optionalItemEntity.isPresent()) {
            request.setItem(optionalItemEntity.get());
        }

        request.setDate_req(LocalDateTime.now());
        request.setQuantity(quantity);
        request.setStatus("pending");
        return rrepo.save(request);
    }

    @SuppressWarnings("finally")
    public RequestEntity updateStatus(Long rid, String status) {
        RequestEntity dummy = new RequestEntity();
        ItemEntity dummy2 = new ItemEntity();

        try {
            dummy = rrepo.findById(rid).get();
            dummy2 = irepo.findById(dummy.getItem().getIid()).get();

            dummy.setStatus(status);
            dummy.setDate_app(LocalDateTime.now());

            dummy2.setQuantity(dummy2.getQuantity() - dummy.getQuantity());
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Request " + rid + " does not exist!");
        } finally {
            irepo.save(dummy2);
            return rrepo.save(dummy);
        }
    }

}
