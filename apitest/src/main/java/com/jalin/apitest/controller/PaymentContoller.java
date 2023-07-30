/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jalin.apitest.controller;

import com.jalin.apitest.dto.PaymentRequest;
import com.jalin.apitest.dto.PaymentResponse;
import com.jalin.apitest.entity.TrxPayment;
import com.jalin.apitest.service.TransactionService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Lutfi
 */
@RestController
@RequestMapping("/api/payment")
public class PaymentContoller {
    
    @Autowired
    private TransactionService trxService;
    
    @PostMapping()
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody PaymentRequest pRequest, Errors error){
        
        PaymentResponse response = new PaymentResponse(pRequest);
        
        try {
            
            if (error.hasErrors()) {
                error.getAllErrors().forEach(allError -> {
                    response.addMessage(allError.getDefaultMessage());
                });

                response.setRc("0030");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            String tBillNumber = pRequest.getBill_number();
            String tREfnumber = pRequest.getRef_number();
            List<TrxPayment> tListTransaction = trxService.getTransaction(tBillNumber, tREfnumber);
            System.out.println("Transaction: "+ tBillNumber +"-" +tREfnumber+"-> "+ tListTransaction);
            if (tListTransaction.isEmpty()) {
                response.setRc("0098");
                response.setMessage("Transaction Not Found with refnumber "+ tREfnumber);
            } else {
                TrxPayment transactionData = tListTransaction.get(0);
                
                if(transactionData.getFlag() == 1){
                    response.setRc("0088");
                    response.setMessage("Transaction Already Paid");
                } else {
                    response.setRc("0000");
                    response.setMessage("Sukses");
                    trxService.updateTransaction(new TrxPayment(response));
                }
            }
        } catch (Exception e) {
            response.setRc("0068");
            response.setMessage("Error: "+ e.getMessage());
        }
        
        return ResponseEntity.ok().body(response);
    }
}
