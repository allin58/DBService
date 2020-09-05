package com.example.dbservice.dao;

import com.example.dbservice.entity.Purchase;
import com.example.dbservice.entity.PurchaseDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PurchaseDao extends CrudRepository<Purchase, Integer> {




    @Query( value = " SELECT name,count*price as total FROM (SELECT  product_id, COUNT(product_id)\n" +
            "    FROM public.purchase WHERE customer_id = :customerId AND date BETWEEN :startDate AND :endDate \n" +
            "    GROUP BY product_id) AS pur INNER JOIN product AS prod ON pur.product_id = prod.id ORDER BY total DESC",
            nativeQuery = true)
    List<PurchaseDetails> purchaseDetailsByCustomerId(@Param("customerId") Integer customerId,
                                                      @Param("startDate") Timestamp startDate,
                                                      @Param("endDate") Timestamp endDate);


}
