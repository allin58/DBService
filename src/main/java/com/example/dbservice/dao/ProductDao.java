package com.example.dbservice.dao;

import com.example.dbservice.entity.Customer;
import com.example.dbservice.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
}

