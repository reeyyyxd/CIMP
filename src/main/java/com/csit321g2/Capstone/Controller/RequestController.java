package com.csit321g2.Capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.Capstone.Entity.RequestEntity;
import com.csit321g2.Capstone.Service.RequestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@CrossOrigin(origins = {"http://localhost:5173", "http://10.241.4.80:5173"})
@RequestMapping("/request")
public class RequestController {

    @Autowired
    RequestService rserv;

    @PostMapping("/add")
    public RequestEntity addRequest(@RequestParam Long iid) {
        return rserv.addRequest(iid);
    }

    @GetMapping("/all")
    public List<RequestEntity> getAllItems(){
        return rserv.getAllItems();
    }

    @GetMapping("/getStats")
    public List<Object> getStats() {
        return rserv.getStats();
    }
    

    @GetMapping("/getPending")
    public List<RequestEntity> getPending() {
        return rserv.getPending();
    }

    @GetMapping("/getApproved")
    public List<RequestEntity> getApproved() {
        return rserv.getApproved();
    }
    

    @PutMapping("/update")
    public RequestEntity updateStatus(@RequestParam Long rid, @RequestParam String status) {
        return rserv.updateStatus(rid, status);
    }

    @GetMapping("/user/{uid}")
    public ResponseEntity<List<RequestEntity>> getRequestsByUser(@PathVariable Long uid) {
        List<RequestEntity> requests = rserv.getRequestsByUser(uid);
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/byItemStatus")
    public ResponseEntity<List<RequestEntity>> getRequestsByItemStatus(@RequestParam String status) {
        List<RequestEntity> requests = rserv.getRequestsByItemStatus(status);
        if (requests.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(requests);
    }
}
