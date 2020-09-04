package com.example.dbservice.criteria;

import com.example.dbservice.dao.CustomerDao;
import com.example.dbservice.entity.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MinExpensesCriteria {

    @Autowired
    CustomerDao customerDao;

    public JSONObject execute(JSONObject inputJSON) {

        return null;
    }
}
