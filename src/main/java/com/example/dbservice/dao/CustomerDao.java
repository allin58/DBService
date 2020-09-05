package com.example.dbservice.dao;

import com.example.dbservice.entity.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@Repository
public interface CustomerDao  extends CrudRepository<Customer, Integer> {

    List<Customer> findByLastName(String lastName);



    @Query( value = "SELECT * FROM customer WHERE customer.id IN (SELECT customer_id FROM public.purchase WHERE" +
            " product_id = :product GROUP BY customer_id HAVING count(product_id) >= :minTimes)",
            nativeQuery = true)
    List<Customer> getCustomersByMinTimeCriteria(@Param("product") Integer product,
                                                 @Param("minTimes") Integer minTimes);



    @Query( value = "SELECT * FROM customer WHERE customer.id IN (SELECT customer_id FROM public.purchase" +
            " GROUP BY customer_id ORDER BY count(product_id) LIMIT :badCustomers)",
            nativeQuery = true)
    List<Customer> getCustomersByBadCustomersCriteria(@Param("badCustomers") Integer badCustomers);


    @Query( value = "SELECT * FROM customer WHERE customer.id IN (SELECT  public.purchase.customer_id FROM public.purchase\n" +
            " INNER JOIN public.product ON public.purchase.product_id = public.product.id GROUP BY public.purchase.customer_id " +
            "HAVING sum(public.product.price) BETWEEN :minExpenses AND :maxExpenses)",
            nativeQuery = true)
    List<Customer> getCustomersByMinMaxExpensesCriteria(@Param("minExpenses") BigDecimal minExpenses, @Param("maxExpenses") BigDecimal maxExpenses);



}

