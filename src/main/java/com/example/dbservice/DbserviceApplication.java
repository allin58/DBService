package com.example.dbservice;

import com.example.dbservice.dao.CustomerDao;
import com.example.dbservice.dao.ProductDao;
import com.example.dbservice.dao.PurchaseDao;
import com.example.dbservice.entity.Customer;
import com.example.dbservice.entity.Purchase;
import com.example.dbservice.service.ServiceHub;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.FileOutputStream;
import java.io.FileReader;


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

			serviceHub.defineService(jsonObject, args[0]);


			/*String str = "Hello";
			FileOutputStream outputStream = new FileOutputStream("fileName.txt");
			byte[] strToBytes = str.getBytes();
			outputStream.write(strToBytes);
			outputStream.close();*/



		};
	}



}
