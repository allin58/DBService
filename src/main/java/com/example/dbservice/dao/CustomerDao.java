package com.example.dbservice.dao;

import com.example.dbservice.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerDao  extends CrudRepository<Customer, Integer> {
}

