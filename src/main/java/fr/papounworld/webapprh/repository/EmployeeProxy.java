package fr.papounworld.webapprh.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import fr.papounworld.webapprh.configuration.CustomProperties;
import fr.papounworld.webapprh.model.Employee;
import lombok.extern.slf4j.Slf4j;


//CETTE CLASSE VA CONTENIR PLUSIEURS METHODES POUR COMMUNIQUER AVEC L API
@Slf4j
@Component
public class EmployeeProxy {
	
	//on va utiliser une propriété custom (ici pour récupéré l'url de base de l'api yoda
	@Autowired
	private CustomProperties customproperties;
	
    /**
    * Get all employees
    * @return An iterable of all employees
    */
	
	//A. CETTE METHODE PERMET DE RECUPERER TOUS LES EMPLOYES DE L API
	//Ils seront stockés dans une Iterable<Employee>
	public Iterable<Employee> getAllEmployees(){
		
		//1.  grâce à notre objet CustomProperties, on récupère l’URL de l’API.
		
		//on met dans un string la base de l'api yoda
		
		String baseApiUrl = customproperties.getApiUrl();
		// on complète l’URL de l’API par le path de l'endpoint (/"employees") à joindre dans un String
				
		String allEmployeesUrl = baseApiUrl + "/employees";
		//: RestTemplate permet d’exécuter une requête HTTP. 
		//On a donc besoin de fournir l’URL, le type de requête (GET, POST, etc.), 
		//et pour finir le type d’objet qui sera retourné.
		//RestTemplate non seulement fait la requête à l’API mais en plus convertit le résultat JSON en objet Java 
		RestTemplate restTemplate = new RestTemplate();
		//on appelle la méthode exchange en transmettant :

		/*
		 *l’URL ;
		 *la méthode HTTP (grâce à l’enum HttpMethod)  : ici c'est un get (car on récupére tous les types d'employées
		 *Null en lieu et place d’un objet HttpEntity, ainsi on laisse un comportement par défaut (on verra plus tard)
		 *le type retour on est censé avoir le type de retour en mode Object.class comme String.class
		 *mais ici je suis obligé d’utiliser un objet ParameterizedTypeReference car /employees renvoie un objet Iterable<Employee>.
		 *et on peut pas faire Iterable<Employee>.class ca existe pas
		 */
		ResponseEntity<Iterable<Employee>> response = 
				restTemplate.exchange(allEmployeesUrl, HttpMethod.GET,null,new ParameterizedTypeReference<Iterable<Employee>>() {
		});
	
		log.debug("Get Employees call" + response.getStatusCode().toString());
		
	//	on récupère notre objet Iterable<Employee> grâce à la méthode getBody() de l’objet Response.
		return response.getBody();
	}
	
	
	//B. CETTE METHODE PERMET CREER UN EMPLOYEE DANS L API
	
	public Employee createEmployee(Employee employee) {
		//On créé l'url de l'api à appelert
		String baseApiUrl = customproperties.getApiUrl();


				
		String createEmployesUrl = baseApiUrl + "/employee" ;
		//RestTemplate permet d’exécuter une requête HTTP
		RestTemplate restTemplate = new RestTemplate();
		//La grande différence correspond au nouvel objet HttpEntity 
		//qui, comme vous le constatez, a été instancié avec 
		//en paramètre du constructeur l’objet Employee 


		HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
		
		//on appelle la méthode exchange pour récupérer la réponse
		ResponseEntity<Employee> response  = restTemplate.exchange(createEmployesUrl, HttpMethod.POST,request,Employee.class);
		
		log.debug("Create employee call" + response.getStatusCode().toString());
		
		//	on récupère notre objet Employee grâce à la méthode getBody() de l’objet Response.
			return response.getBody();
	}
	
	
	//C. CETTE METHODE PERMET DE METTRE A RECUPERER UN EMPLOYEE DANS L API
	
	public Employee getEmployee(int id){
		
		String baseApiUrl = customproperties.getApiUrl();
		String getEmployesUrl = baseApiUrl + "/employee/"  + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Employee> response  = restTemplate.exchange(getEmployesUrl, HttpMethod.GET,null,Employee.class);
		
	log.debug("get employee call" + response.getStatusCode().toString());
		
		
			return response.getBody() ;
	}
	
	//D. CETTE METHODE PERMET DE METTRE A JOUR UN EMPLOYEE DANS L API
	
	/**
	 * Update an employee - using the PUT HTTP Method.
	 * @param e Existing employee to update
	 */
	
	public Employee updateEmployee(Employee employee) {
		//On créé l'url de l'api à appelert
		String baseApiUrl = customproperties.getApiUrl();


				
		String updateEmployesUrl = baseApiUrl + "/employee/"  + employee.getId();
		//RestTemplate permet d’exécuter une requête HTTP
		RestTemplate restTemplate = new RestTemplate();
		//La grande différence correspond au nouvel objet HttpEntity 
		//qui, comme vous le constatez, a été instancié avec 
		//en paramètre du constructeur l’objet Employee 


		HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
		
		//on appelle la méthode exchange pour récupérer la réponse
		ResponseEntity<Employee> response  = restTemplate.exchange(updateEmployesUrl, HttpMethod.PUT,request,Employee.class);
	
		log.debug("Update employee call" + response.getStatusCode().toString());
		
		//	on récupère notre objet Employee grâce à la méthode getBody() de l’objet Response.
			return response.getBody();
	}
	
	
	//E. CETTE METHODE PERMET D'EFFACER EMPLOYEE DANS L API
	public void deleteEmployee(int id) {
		String baseApiUrl = customproperties.getApiUrl();
		String deleteEmployesUrl = baseApiUrl + "/employee/"  + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<Void> response  = restTemplate.exchange(deleteEmployesUrl, HttpMethod.DELETE,null,Void.class);
		log.debug("Delete Employee call " + response.getStatusCode().toString());
		
	}
	

}
