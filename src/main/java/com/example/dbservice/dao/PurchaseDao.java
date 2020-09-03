package com.example.dbservice.dao;

import com.example.dbservice.entity.Product;
import com.example.dbservice.entity.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDao extends CrudRepository<Purchase, Integer> {
}
