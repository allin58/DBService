package com.example.dbservice.service;


import com.example.dbservice.dao.CustomerDao;
import com.example.dbservice.dao.ProductDao;
import com.example.dbservice.dao.PurchaseDao;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    CustomerDao customerDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    PurchaseDao purchaseDao;

    public JSONObject search(JSONObject inputJSON) {
        JSONArray criterias = (JSONArray) inputJSON.get("criterias");
        for (Object criteria : criterias) {
            System.out.println(criteria);
        }


        return null;
    }



}
