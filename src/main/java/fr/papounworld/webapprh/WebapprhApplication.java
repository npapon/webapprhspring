package fr.papounworld.webapprh;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.papounworld.webapprh.configuration.CustomProperties;
import fr.papounworld.webapprh.model.Employee;
import fr.papounworld.webapprh.repository.EmployeeProxy;
import lombok.Getter;

@SpringBootApplication
public class WebapprhApplication implements CommandLineRunner {

	@Autowired
	private CustomProperties custumProperties ;
	
	@Autowired
	private EmployeeProxy employeeProxy;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(WebapprhApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	System.out.println(custumProperties.getApiUrl());
	Iterable<Employee> iterable = employeeProxy.getAllEmployees();
	Iterator<Employee> iterator = iterable.iterator();
	
	while(iterator.hasNext()) {
		System.out.println(iterator.next());
	}
	
	}

}
