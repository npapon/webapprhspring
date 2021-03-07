package fr.papounworld.webapprh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.papounworld.webapprh.model.Employee;
import fr.papounworld.webapprh.repository.EmployeeProxy;
import lombok.Data;

//on utilise Data pour ne pas créé de getter et setter (lombok)
//on utilise Service pour qu'on puisse utiliser ce bean sans l'instancier
@Data
@Service
public class EmployeeService {
	
	//on instancie l'objet repository qui communique avec la base
	@Autowired
	private EmployeeProxy employeeProxy;
	
	//permet de récupéré un employé
	public Employee getEmployee(final int id) {
		return employeeProxy.getEmployee(id);
		
	}
	
	//permet de récupérer tous les employés
	public Iterable<Employee> getAllEmployees(){
		return employeeProxy.getAllEmployees();
	}
	
	//permet d'effacer un employé
	
	public void deleteEmployee(final int id) {
		employeeProxy.deleteEmployee(id);
		
	}
	
	public Employee saveEmployee(Employee employee) {
		
		Employee savedEmployee  ;
		
	      // Règle de gestion : Le nom de famille doit être mis en majuscule.
        employee.setLastname(employee.getLastname().toUpperCase());
        
		if(employee.getId() ==null) {
			savedEmployee  = employeeProxy.createEmployee(employee);
		}
		else
		{
			savedEmployee  = employeeProxy.updateEmployee(employee);
		}
		
		return savedEmployee;
		
	}
	
	
	
	

}
