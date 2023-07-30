/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jalin.apitest.dto;

import com.jalin.apitest.entity.BillPayment;


/**
 *
 * @author Lutfi
 */
public class InquiryResponse extends InquiryRequest{
    
    private String ref_number;
    private String bill_name;
    private double bill_amount;
    private double bill_admin;
    private double bill_total;

    public InquiryResponse(InquiryRequest request) {
        super(request);
    }

    public void setBillData(BillPayment pBill, String pRefnumber){
        setBill_amount(pBill.getBill_amount());
        setBill_admin(pBill.getBill_admin());
        setBill_total(pBill.getBill_total());
        setBill_name(pBill.getBill_name());
        setRef_number(pRefnumber);
    }

    public String getRef_number() {
        return ref_number;
    }

    public void setRef_number(String ref_number) {
        this.ref_number = ref_number;
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
