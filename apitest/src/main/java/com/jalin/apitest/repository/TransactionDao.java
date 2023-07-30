/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jalin.apitest.repository;

import com.jalin.apitest.entity.TrxPayment;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Lutfi
 */
public interface TransactionDao extends CrudRepository<TrxPayment, Long>{
    
    @Query(
            "SELECT p From TrxPayment p WHERE p.bill_number  = :billnumber AND p.reff_number= :reffnumber"
    )
    List<TrxPayment> findTransaction(@Param("billnumber") String pBillNumber, @Param("reffnumber") String pRefnumber);
    
    @Modifying
    @Query(
            "UPDATE TrxPayment p "
                    + "SET p.flag = 1, p.pay_reff_number = :payreffnumber, p.updated_at = NOW() "
                    + "WHERE p.bill_number = :billnumber "
                    + "AND p.reff_number = :reffnumber"
    )
    void updateTransaction(
            @Param("billnumber") String pBillNumber, 
            @Param("reffnumber") String pRefnumber,
            @Param("payreffnumber") String pPayRefnumber
    );
}
