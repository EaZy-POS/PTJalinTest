/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jalin.apitest.entity;

import com.jalin.apitest.dto.InquiryResponse;
import com.jalin.apitest.dto.PaymentResponse;
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
@Table(name = "trx_payment")
public class TrxPayment implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transaction_id;
    
    @Column(columnDefinition = "TIMESTAMP ", insertable = false, updatable = false)
    private Timestamp created_at;
    
    @Column(columnDefinition = "TIMESTAMP NULL DEFAULT NULL ", insertable = false, updatable = false, nullable = true)
    private Timestamp updated_at;
    
    @Column(length = 32, unique = true, nullable = false)
    private String reff_number;
    
    @Column(length = 32, nullable = true)
    private String pay_reff_number;
    
    @Column(length = 5)
    private String bank_id;
    
    @Column(length = 5)
    private String chanel_id;
    
    @Column(length = 32, nullable = false)
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
    private int flag;

    public TrxPayment() {
    }

    public TrxPayment(InquiryResponse pInquiryResponse) {
        initBillData(pInquiryResponse);
    }
    
    public TrxPayment(PaymentResponse pInquiryResponse) {
        initBillData(pInquiryResponse);
    }
    
    private void initBillData(InquiryResponse pResponse){
        setBill_amount(pResponse.getBill_amount());
        setBill_admin(pResponse.getBill_admin());
        setBill_total(pResponse.getBill_total());
        setBill_name(pResponse.getBill_name());
        setBill_number(pResponse.getBill_number());
        setReff_number(pResponse.getRef_number());
        setCreated_at(new Timestamp(System.currentTimeMillis()));
        setBank_id(pResponse.getBank_id());
        setChanel_id(pResponse.getChanel_id());
    }
    
    private void initBillData(PaymentResponse pResponse){
        setBill_amount(pResponse.getBill_amount());
        setBill_admin(pResponse.getBill_admin());
        setBill_total(pResponse.getBill_total());
        setBill_name(pResponse.getBill_name());
        setBill_number(pResponse.getBill_number());
        setReff_number(pResponse.getRef_number());
        setCreated_at(new Timestamp(System.currentTimeMillis()));
        setBank_id(pResponse.getBank_id());
        setChanel_id(pResponse.getChanel_id());
        setPay_reff_number(pResponse.getPay_reff_number());
    }
    
    public long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(long transaction_id) {
        this.transaction_id = transaction_id;
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

    public String getReff_number() {
        return reff_number;
    }

    public void setReff_number(String reff_number) {
        this.reff_number = reff_number;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getChanel_id() {
        return chanel_id;
    }

    public void setChanel_id(String chanel_id) {
        this.chanel_id = chanel_id;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getPay_reff_number() {
        return pay_reff_number;
    }

    public void setPay_reff_number(String pay_reff_number) {
        this.pay_reff_number = pay_reff_number;
    }
    
}
