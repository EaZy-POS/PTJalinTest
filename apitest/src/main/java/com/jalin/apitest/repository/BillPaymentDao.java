/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jalin.apitest.repository;

import com.jalin.apitest.entity.BillPayment;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Lutfi
 */
public interface BillPaymentDao extends CrudRepository<BillPayment, Long> {
    @Query(
            "SELECT p From BillPayment p WHERE p.bill_number  = :billnumber AND p.bill_status =0"
    )
    List<BillPayment> findBillPayment(@Param("billnumber") String pBillName);
    
    @Modifying
    @Query(
            "UPDATE BillPayment p SET p.bill_status=1, p.updated_at= NOW() WHERE p.bill_number  = :billnumber AND p.bill_status =0"
    )
    void updateBillPaymnet(@Param("billnumber") String pBillName);
}
