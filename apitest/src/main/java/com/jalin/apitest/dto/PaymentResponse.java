/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jalin.apitest.dto;

/**
 *
 * @author Lutfi
 */
public class PaymentResponse extends PaymentRequest{
    
    private String rc;
    private String message;
    
    public PaymentResponse(PaymentRequest request) {
        super(request);
    }
    
    public String getRc() {
        return rc;
    }

    public void setRc(String rc) {
        this.rc = rc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public void addMessage(String pMessage){
        if (getMessage() == null) {
            setMessage(pMessage);
        }else{
            setMessage(getMessage().concat(", ").concat(pMessage));
        }
    }
}
