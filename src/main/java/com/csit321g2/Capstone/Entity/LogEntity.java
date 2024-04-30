package com.csit321g2.Capstone.Entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbllog")
public class LogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long logid;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "uid")
    private UserEntity user;
    
    @ManyToOne
    @JoinColumn(name = "propertyTag", referencedColumnName = "propertyTag")
    private ItemsEntity item;

    private LocalDate date;
    private LocalTime time;
    private String type;
    
    public LogEntity() {
    }

    public LogEntity(UserEntity user, ItemsEntity item, LocalDate date, LocalTime time, String type) {
        this.user = user;
        this.item = item;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    public long getLogid() {
        return logid;
    }

    public void setLogid(long logid) {
        this.logid = logid;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ItemsEntity getItem() {
        return item;
    }

    public void setItem(ItemsEntity item) {
        this.item = item;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
}
