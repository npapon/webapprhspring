package fr.papounworld.webapprh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.papounworld.webapprh.model.Employee;
import fr.papounworld.webapprh.service.EmployeeService;

@Controller
public class EmployeeController {
	
	 @Autowired
	 EmployeeService employeeService;
	
	 //L’objet Model (org.springframework.ui.Model) a été ajouté en paramètre de la méthode home(). 
	 //Grâce à cela, Spring se charge de nous fournir une instance de cet objet.
	@GetMapping("/")
	public String home(Model model) {
		
		//On récupére tous les employées en base
		Iterable<Employee> tousLesEmployees =  employeeService.getAllEmployees();
		//addAttribute qui permet d’ajouter à mon Model un objet
		//Le premier paramètre spécifie le nom (de mon choix) 
		//et le deuxième l’objet (ici, la liste des employés en Iterable). 
		model.addAttribute("tousLesEmployes",tousLesEmployees);
		return "home";
	}
	
	
	@GetMapping("/deleteEmployee/{id}")
	public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
	    employeeService.deleteEmployee(id);
	    return new ModelAndView("redirect:/");
	}

	
	@GetMapping("/supervision")
	public void supervision() {}
	
	@PostMapping("/saveEmployee")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		employeeService.saveEmployee(employee);
	    return new ModelAndView("redirect:/");
	}
	
	@GetMapping("/createEmployee")
	public String createEmployee(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "creationEmploye";
	}
	
	//on récupère l'employée dans un attribut et on arrive avec sur la page form
	@GetMapping("/updateEmployee/{id}")
	public String updateEmployee(@PathVariable("id") final int id, Model model) {
		Employee employee = employeeService.getEmployee(id);		
		model.addAttribute("employee", employee);	
		return "miseAJourEmployee.html";		
	}
}
