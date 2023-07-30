/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jalin.apitest.service;

import com.jalin.apitest.entity.BillPayment;
import com.jalin.apitest.entity.TrxPayment;
import com.jalin.apitest.repository.BillPaymentDao;
import com.jalin.apitest.repository.TransactionDao;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lutfi
 */
@Service
@Transactional
public class TransactionService {
    
    @Autowired
    private BillPaymentDao billDao;
    
    @Autowired
    private TransactionDao trxDao;
    
    public List<BillPayment> getBillpayment(String pBillId){
        return billDao.findBillPayment(pBillId);
    }
    
    public void insertTransaction(TrxPayment pTransaction){
        trxDao.save(pTransaction);
    }
    
    public List<TrxPayment> getTransaction(String pBillId, String pRefnumber){
        return trxDao.findTransaction(pBillId, pRefnumber);
    }
    
    public void updateTransaction(TrxPayment pTransaction){
        trxDao.updateTransaction(pTransaction.getBill_number(), pTransaction.getReff_number(), pTransaction.getPay_reff_number());
        billDao.updateBillPaymnet(pTransaction.getBill_number());
    }
}
