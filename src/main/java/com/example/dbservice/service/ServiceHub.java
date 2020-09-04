package com.example.dbservice.service;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceHub {

    @Autowired
    SearchService searchService;

    @Autowired
    StatServise statServise;


   public JSONObject defineService(JSONObject inputJSON, String serviceName) {
        JSONObject outputJSON = null;

        if(serviceName.equalsIgnoreCase("search"))
             outputJSON = searchService.search(inputJSON);
        else if(serviceName.equalsIgnoreCase("stat"))
             outputJSON = statServise.getStat(inputJSON);

        return outputJSON;
    }



}
