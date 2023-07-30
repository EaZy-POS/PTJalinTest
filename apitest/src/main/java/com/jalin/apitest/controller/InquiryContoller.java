/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jalin.apitest.controller;

import com.jalin.apitest.dto.InquiryRequest;
import com.jalin.apitest.dto.InquiryResponse;
import com.jalin.apitest.entity.BillPayment;
import com.jalin.apitest.entity.TrxPayment;
import com.jalin.apitest.service.TransactionService;
import java.util.List;
import java.util.UUID;
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
@RequestMapping("/api/inquiry")
public class InquiryContoller {
    
    @Autowired
    private TransactionService trxService;
    
    @PostMapping()
    public ResponseEntity<InquiryResponse> create(@Valid @RequestBody InquiryRequest pRequest, Errors error){
        
        InquiryResponse response = new InquiryResponse(pRequest);
        
        try {
            
            if (error.hasErrors()) {
                error.getAllErrors().forEach(allError -> {
                    response.addMessage(allError.getDefaultMessage());
                });

                response.setRc("0030");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
            
            String tBillNumber = pRequest.getBill_number();
            List<BillPayment> tListBill = trxService.getBillpayment(tBillNumber);
            System.out.println("Bill Number: "+ tBillNumber +" " +tListBill);
            if (tListBill.isEmpty()) {
                response.setRc("0014");
                response.setMessage("No Data Found");
            } else {
                BillPayment billData = tListBill.get(0);
                response.setBillData(billData, UUID.randomUUID().toString().replace("-", ""));
                response.setRc("0000");
                response.setMessage("Sukses");
                trxService.insertTransaction(new TrxPayment(response));
            }
        } catch (Exception e) {
            response.setRc("0068");
            response.setMessage("Error: "+ e.getMessage());
        }
        
        return ResponseEntity.ok().body(response);
    }
}
