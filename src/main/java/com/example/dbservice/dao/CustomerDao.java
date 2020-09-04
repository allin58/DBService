package com.example.dbservice.dao;

import com.example.dbservice.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerDao  extends CrudRepository<Customer, Integer> {

    List<Customer> findByLastName(String lastName);


    @Query( value = "SELECT customer_id, count(product_id) FROM public.purchase where product_id = :product group by customer_id HAVING count(product_id) >= :minTimes",
            nativeQuery = true)
    List<Integer> getCustomersByMinTimeCriteria(@Param("product") Integer product,
                                                @Param("minTimes") Integer minTimes);






}

