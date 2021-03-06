package com.example.dbservice.criteria;

import com.example.dbservice.dao.CustomerDao;
import com.example.dbservice.dao.ProductDao;
import com.example.dbservice.dao.PurchaseDao;
import com.example.dbservice.entity.Customer;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MinTimeCriteria   {

    @Autowired
    CustomerDao customerDao;


    @Autowired
    ProductDao productDao;


    public JSONObject execute(JSONObject inputJSON) {
        JSONObject result = new JSONObject();
        result.put("criteria",inputJSON);
        Integer product_id = null;
        Integer minTimes = null;
        JSONArray listOfCustomers = new JSONArray();

        try {
            minTimes = Integer.parseInt(inputJSON.get("minTimes").toString());
        } catch (NumberFormatException e) {
            result.put("type","error");
            result.put("message","wrong number");
            return result;
        }


        try {
            product_id = productDao.findByName(inputJSON.get("productName").toString()).get(0).getId();

        } catch (Exception e) {
            result.put("type","error");
            result.put("message","no such product found");
            return result;
        }


        List<Customer> customers = customerDao.getCustomersByMinTimeCriteria(product_id, minTimes);
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
