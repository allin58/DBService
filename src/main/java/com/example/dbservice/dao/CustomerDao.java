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


    @Query( value = "SELECT customer_id, count(product_id) FROM public.purchase group by customer_id ORDER BY count(product_id) LIMIT :badCustomers",
            nativeQuery = true)
    List<Integer> getCustomersByBadCustomersCriteria(@Param("badCustomers") Integer badCustomers);



/*    @Query( value = "SELECT  public.purchase.customer_id, sum(public.product.price) FROM public.purchase\n" +
            "inner join public.product on public.purchase.product_id = public.product.id group by public.purchase.customer_id\n" +
            "ORDER BY sum LIMIT :badCustomers",
            nativeQuery = true)
    List<Integer> getCustomersByMinExpensesCriteria(@Param("badCustomers") Integer badCustomers);*/



}

