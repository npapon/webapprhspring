package fr.papounworld.webapprh.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
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

}
