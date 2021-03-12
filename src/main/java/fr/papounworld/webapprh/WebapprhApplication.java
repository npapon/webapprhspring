package fr.papounworld.webapprh;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.papounworld.webapprh.configuration.CustomProperties;
import fr.papounworld.webapprh.model.Employee;
import fr.papounworld.webapprh.repository.EmployeeProxy;
import fr.papounworld.webapprh.service.EmployeeService;
import lombok.Getter;

@SpringBootApplication
public class WebapprhApplication implements CommandLineRunner {


	@Autowired
	private CustomProperties custumProperties ;
	
	@Autowired
	private EmployeeProxy employeeProxy;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(WebapprhApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		/*
	System.out.println(custumProperties.getApiUrl());
	Iterable<Employee> iterable = employeeProxy.getAllEmployees();
	Iterator<Employee> iterator = iterable.iterator();
	
	while(iterator.hasNext()) {
		System.out.println(iterator.next());
	}
	
	Employee sasha = new Employee();
	sasha.setFirstname("Sasha");
	sasha.setLastname("Papon");
	sasha.setMail("sasha@cool.com");
	sasha.setPassword("aaa");
	employeeProxy.createEmployee(sasha);
	
	
	
	
Employee employee3 = employeeProxy.getEmployee(5);
System.out.println(employee3.getFirstname());

employee3.setFirstname("Nigros");
employeeProxy.updateEmployee(employee3);
System.out.println(employee3.getFirstname());

//employeeProxy.deleteEmployee(1);


Employee sasha2 = new Employee();
sasha2.setFirstname("Sasha2");
sasha2.setLastname("Papon");
sasha2.setMail("sasha@cool.com");
sasha2.setPassword("aaa");


employeeService.saveEmployee(sasha2);

Employee employee4 = employeeProxy.getEmployee(8);
employee4.setPassword("gros");

employeeService.saveEmployee( employee4);
*/
	
	}
	


}
