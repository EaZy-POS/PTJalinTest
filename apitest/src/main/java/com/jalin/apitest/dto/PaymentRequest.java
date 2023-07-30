/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jalin.apitest.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Lutfi
 */
public class PaymentRequest extends Message{
    
    @NotEmpty(message = "bank_id is required")
    private String bank_id;
    
    @NotEmpty(message = "chanel_id is required")
    private String chanel_id;
    
    @NotEmpty(message = "bill_number is required")
    private String bill_number;
    
    @NotEmpty(message = "ref_number is required")
    private String ref_number;
    
    @NotEmpty(message = "pay_reff_number is required")
    private String pay_reff_number;
    private String bill_name;
    private double bill_amount;
    private double bill_admin;
    
    @NotNull(message = "bill_total is required")
    private double bill_total;

    public PaymentRequest() {
    }

    public PaymentRequest(PaymentRequest request){
        initData(request);
    }
    
    private void initData(PaymentRequest request){
        setBank_id(request.getBank_id());
        setChanel_id(request.getChanel_id());
        setBill_number(request.getBill_number());
        setRef_number(request.getRef_number());
        setPay_reff_number(request.getPay_reff_number());
        setBill_name(request.getBill_name());
        setBill_amount(request.getBill_amount());
        setBill_admin(request.getBill_admin());
        setBill_total(request.getBill_total());
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

    public String getRef_number() {
        return ref_number;
    }

    public void setRef_number(String ref_number) {
        this.ref_number = ref_number;
    }

    public String getPay_reff_number() {
        return pay_reff_number;
    }

    public void setPay_reff_number(String pay_reff_number) {
        this.pay_reff_number = pay_reff_number;
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
    
    
}
