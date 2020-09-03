package com.example.dbservice;

import com.example.dbservice.dao.CustomerDao;
import com.example.dbservice.dao.PurchaseDao;
import com.example.dbservice.entity.Customer;
import com.example.dbservice.entity.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;



@SpringBootApplication
public class DbserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbserviceApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {


			for (Purchase purchase : purchaseDao.findAll()) {
				System.out.println(purchase.getCustomer().getFirstName() + " " + purchase.getProduct().getName() + " " + purchase.getDate());
			}



		};
	}

    @Autowired
	CustomerDao customerDao;

	@Autowired
	PurchaseDao purchaseDao;

}
