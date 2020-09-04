package com.example.dbservice.criteria;

import com.example.dbservice.dao.CustomerDao;
import com.example.dbservice.dao.ProductDao;
import com.example.dbservice.dao.PurchaseDao;
import com.example.dbservice.entity.Customer;
import com.example.dbservice.entity.Product;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LastNameCriteria   {


    @Autowired
    CustomerDao customerDao;

    @Autowired
    PurchaseDao purchaseDao;

    @Autowired
    ProductDao productDao;


    public JSONObject execute(JSONObject inputJSON) {
        JSONObject result = new JSONObject();
        result.put("criteria",inputJSON);

        String lastName = inputJSON.get("lastName").toString();
        List<Customer> byLastName = customerDao.findByLastName(lastName);
        if (byLastName.isEmpty()) {
            result.put("type","error");
            result.put("message","no such customer found");
            return result;
        }

        JSONArray listOfCustomers = new JSONArray();
        for (Customer customer : byLastName) {
            JSONObject tempJSON = new JSONObject();
            tempJSON.put("lastName",customer.getLastName());
            tempJSON.put("firstName",customer.getFirstName());
            listOfCustomers.add(tempJSON);
        }


        result.put("results",listOfCustomers);


        return result;
    }
}
