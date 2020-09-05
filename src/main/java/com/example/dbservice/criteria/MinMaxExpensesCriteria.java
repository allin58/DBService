package com.example.dbservice.criteria;

import com.example.dbservice.dao.CustomerDao;
import com.example.dbservice.entity.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class MinMaxExpensesCriteria {

    @Autowired
    CustomerDao customerDao;

    public JSONObject execute(JSONObject inputJSON) {
        JSONObject result = new JSONObject();
        result.put("criteria",inputJSON);
        BigDecimal minExpenses = null;
        BigDecimal maxExpenses = null;
        JSONArray listOfCustomers = new JSONArray();

        try {
            minExpenses = new BigDecimal(inputJSON.get("minExpenses").toString());
            maxExpenses = new BigDecimal(inputJSON.get("maxExpenses").toString());
        } catch (Exception e) {
            result.put("type","error");
            result.put("message","wrong number");
            return result;
        }



        List<Customer> customers = customerDao.getCustomersByMinMaxExpensesCriteria(minExpenses, maxExpenses);
        for (Customer customer : customers) {
            JSONObject tempJSON = new JSONObject();
            tempJSON.put("lastName",customer.getLastName());
            tempJSON.put("firstName",customer.getFirstName());
            listOfCustomers.add(tempJSON);
        }


        result.put("results",listOfCustomers);



        return result;
    }
}
