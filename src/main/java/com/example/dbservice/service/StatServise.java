package com.example.dbservice.service;

import com.example.dbservice.dao.CustomerDao;
import com.example.dbservice.dao.ProductDao;
import com.example.dbservice.dao.PurchaseDao;
import com.example.dbservice.entity.Customer;
import com.example.dbservice.entity.PurchaseDetails;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class StatServise {


    @Autowired
    CustomerDao customerDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    PurchaseDao purchaseDao;

    public JSONObject getStat(JSONObject inputJSON) {
        JSONObject result = new JSONObject();
        Timestamp startTimestamp = null;
        Timestamp endTimestamp = null;
        long days;
        try {
            startTimestamp = Timestamp.valueOf(inputJSON.get("startDate").toString() + " 00:00:00.000");
            endTimestamp = Timestamp.valueOf(inputJSON.get("endDate").toString() + " 00:00:00.000");
            days = TimeUnit.MILLISECONDS.toDays(endTimestamp.getTime() - startTimestamp.getTime()) + 1 ;
        } catch (Exception e) {
            result.put("type","error");
            result.put("message","date format is wrong");
            return result;
        }


        result.put("type","stat");
        result.put("totalDays",days);

        JSONArray customersData = new JSONArray();

        BigDecimal globalTotalExpenses = new BigDecimal(0);
        for (Customer customer : customerDao.findAll()) {
            JSONObject customerData = new JSONObject();
            customerData.put("name", customer.getLastName() + " " + customer.getFirstName());
            List<PurchaseDetails> purchaseDetails = purchaseDao.purchaseDetailsByCustomerId(customer.getId(), startTimestamp, endTimestamp);
            JSONArray purchases = new JSONArray();
            BigDecimal totalExpenses = new BigDecimal(0);
            for (PurchaseDetails purchaseDetail : purchaseDetails) {
                JSONObject perchase = new JSONObject();
                perchase.put("name",purchaseDetail.getName());
                perchase.put("expenses",purchaseDetail.getTotal());
                purchases.add(perchase);
                totalExpenses = totalExpenses.add(purchaseDetail.getTotal());
                globalTotalExpenses = globalTotalExpenses.add(totalExpenses);
            }
           
            customerData.put("purchases",purchases);
            customerData.put("totalExpenses",totalExpenses);
            customersData.add(customerData);
          }

        long countOfCustomers = customerDao.count();

        result.put("customers",customersData);
        result.put("totalExpenses",globalTotalExpenses);
        result.put("avgExpenses",123);


        return result;
    }


}
