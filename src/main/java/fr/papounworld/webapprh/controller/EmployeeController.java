package fr.papounworld.webapprh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import fr.papounworld.webapprh.model.Employee;
import fr.papounworld.webapprh.service.EmployeeService;

@Controller
public class EmployeeController {
	
	 @Autowired
	 EmployeeService employeeService;
	
	 //L’objet Model (org.springframework.ui.Model) a été ajouté en paramètre de la méthode home(). 
	 //Grâce à cela, Spring se charge de nous fournir une instance de cet objet.
	@GetMapping("/home")
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
	    return new ModelAndView("redirect:/home");
	}

	
	@GetMapping("/supervision")
	public void supervision() {}
}
