package com.csit321g2.Capstone.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tblrequest")
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long rid;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "propertyTag")
    private ItemEntity item;

    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime date_req;

    @JsonFormat(pattern = "MM-dd-yyyy HH:mm:ss")
    private LocalDateTime date_app;

    private String status;

    private int quantity;

    public RequestEntity() {
    }

    public RequestEntity(ItemEntity item, LocalDateTime date_req, LocalDateTime date_app, String status, int quantity) {
        this.item = item;
        this.date_req = date_req;
        this.date_app = date_app;
        this.status = status;
        this.quantity = quantity;
    }

    public RequestEntity(ItemEntity item, LocalDateTime date_req, int quantity) {
        this.item = item;
        this.date_req = date_req;
        this.quantity = quantity;
    }

    public long getRid() {
        return rid;
    }

    public void setRid(long rid) {
        this.rid = rid;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public LocalDateTime getDate_req() {
        return date_req;
    }

    public void setDate_req(LocalDateTime date_req) {
        this.date_req = date_req;
    }

    public LocalDateTime getDate_app() {
        return date_app;
    }

    public void setDate_app(LocalDateTime date_app) {
        this.date_app = date_app;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
