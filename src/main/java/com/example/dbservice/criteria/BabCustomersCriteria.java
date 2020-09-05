package com.example.dbservice.criteria;

import com.example.dbservice.dao.CustomerDao;
import com.example.dbservice.entity.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BabCustomersCriteria {


    @Autowired
    CustomerDao customerDao;

    public JSONObject execute(JSONObject inputJSON) {
        JSONObject result = new JSONObject();
        result.put("criteria",inputJSON);

        Integer badCustomers = null;
        JSONArray listOfCustomers = new JSONArray();

        try {
            badCustomers = Integer.parseInt(inputJSON.get("badCustomers").toString());
            if(badCustomers < 1) throw new Exception();
        } catch (Exception e) {
            result.put("type","error");
            result.put("message","wrong number");
            return result;
        }


        List<Customer> Customers = customerDao.getCustomersByBadCustomersCriteria(badCustomers);
        for (Customer customer : Customers) {
            JSONObject tempJSON = new JSONObject();
            tempJSON.put("lastName",customer.getLastName());
            tempJSON.put("firstName",customer.getFirstName());
            listOfCustomers.add(tempJSON);
        }


        result.put("results",listOfCustomers);


        return result;
    }
}