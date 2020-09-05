package com.example.dbservice;

import com.example.dbservice.service.ServiceHub;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.FileReader;
import java.io.FileWriter;


@SpringBootApplication
public class DbserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbserviceApplication.class, args);
	}

	@Autowired
	ServiceHub serviceHub;

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			JSONParser parser = new JSONParser();
			Object obj = parser.parse(new FileReader(args[1]));
			JSONObject jsonObject = (JSONObject) obj;

			JSONObject outputJSON = serviceHub.defineService(jsonObject, args[0]);

			FileWriter file = new FileWriter(args[2]);
			file.write(outputJSON.toJSONString());
			file.flush();
			file.close();
		};
	}



}
