/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jalin.apitest.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Lutfi
 */
@Entity
@Table(name = "bill_payment")
public class BillPayment implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long product_id;
    
    @Column(columnDefinition = "TIMESTAMP ", insertable = false, updatable = false)
    private Timestamp created_at;
    
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL", insertable = false, updatable = false, nullable = true)
    private Timestamp updated_at;
    
    @Column(length = 32, unique = true, nullable = false)
    private String bill_number;
    
    @Column(length = 50, nullable = false)
    private String bill_name;
    
    @Column(length = 12, columnDefinition = "DOUBLE(12,0) Default '0'")
    private double bill_amount;
    
    @Column(length = 12, columnDefinition = "DOUBLE(12,0) Default '0'")
    private double bill_admin;
    
    @Column(length = 12, columnDefinition = "DOUBLE(12,0) Default '0'")
    private double bill_total;
    
    @Column(length = 1, columnDefinition = "INT(1) Default '0'")
    private int bill_status;

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getBill_number() {
        return bill_number;
    }

    public void setBill_number(String bill_number) {
        this.bill_number = bill_number;
    }

    public String getBill_name() {
        return bill_name;
    }

    public void setBill_name(String bill_name) {
        this.bill_name = bill_name;
    }

    public double getBill_amount() {
        return bill_amount;
    }

    public void setBill_amount(double bill_amount) {
        this.bill_amount = bill_amount;
    }

    public double getBill_admin() {
        return bill_admin;
    }

    public void setBill_admin(double bill_admin) {
        this.bill_admin = bill_admin;
    }

    public double getBill_total() {
        return bill_total;
    }

    public void setBill_total(double bill_total) {
        this.bill_total = bill_total;
    }

    public int getBill_status() {
        return bill_status;
    }

    public void setBill_status(int bill_status) {
        this.bill_status = bill_status;
    }
    
    
}
