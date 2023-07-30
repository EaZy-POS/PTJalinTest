/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jalin.apitest.dto;

import javax.validation.constraints.NotEmpty;

/**
 *
 * @author Lutfi
 */
public class InquiryRequest extends Message{
    
    @NotEmpty(message = "bank_id is required")
    private String bank_id;
    
    @NotEmpty(message = "chanel_id is required")
    private String chanel_id;
    
    @NotEmpty(message = "bill_number is required")
    private String bill_number;

    public InquiryRequest() {
    }

    public InquiryRequest(InquiryRequest request) {
        initData(request);
    }
    
    private void initData(InquiryRequest request){
        setBank_id(request.getBank_id());
        setChanel_id(request.getChanel_id());
        setBill_number(request.getBill_number());
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

    
}
