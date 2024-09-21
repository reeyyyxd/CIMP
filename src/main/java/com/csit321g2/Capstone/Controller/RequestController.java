package com.csit321g2.Capstone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.csit321g2.Capstone.Entity.RequestEntity;
import com.csit321g2.Capstone.Service.RequestService;

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

    @PutMapping("/update")
    public RequestEntity updateStatus(@RequestParam Long rid, @RequestParam String status) {
        return rserv.updateStatus(rid, status);
    }
}
