package com.example.dbservice.service;


import com.example.dbservice.criteria.BabCustomersCriteria;
import com.example.dbservice.criteria.LastNameCriteria;
import com.example.dbservice.criteria.MinExpensesCriteria;
import com.example.dbservice.criteria.MinTimeCriteria;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class SearchService {

    @Autowired
    LastNameCriteria lastNameCriteria;

    @Autowired
    BabCustomersCriteria babCustomersCriteria;

    @Autowired
    MinExpensesCriteria minExpensesCriteria;

    @Autowired
    MinTimeCriteria minTimeCriteria;

    public JSONObject search(JSONObject inputJSON) {
        JSONArray criterias = (JSONArray) inputJSON.get("criterias");
        JSONObject outputJSON = new JSONObject();
        outputJSON.put("type","search");
        JSONArray resultJSON = new JSONArray();

        for (Object criteria : criterias) {

            JSONObject criteriaJSON = (JSONObject) criteria;


            if(criteriaJSON.containsKey("lastName")) {
                JSONObject execute = lastNameCriteria.execute(criteriaJSON);
                resultJSON.add(execute);
            }

            if(criteriaJSON.containsKey("minTimes")) {
                JSONObject execute = minTimeCriteria.execute(criteriaJSON);
                resultJSON.add(execute);
            }

            if(criteriaJSON.containsKey("minExpenses")) {
                JSONObject execute = minExpensesCriteria.execute(criteriaJSON);
              //  resultJSON.add(execute);
            }

            if(criteriaJSON.containsKey("badCustomers")) {
                JSONObject execute = babCustomersCriteria.execute(criteriaJSON);
                resultJSON.add(execute);
            }



        }

        outputJSON.put("results",resultJSON);



        return outputJSON;
    }



}
