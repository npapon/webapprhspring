package fr.papounworld.webapprh;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import fr.papounworld.webapprh.configuration.CustomProperties;
import fr.papounworld.webapprh.controller.EmployeeController;
import fr.papounworld.webapprh.repository.EmployeeProxy;
import fr.papounworld.webapprh.service.EmployeeService;


@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeTestsUnitaires {
	
	  @Autowired
	    private MockMvc mockMvc;

	  //on met tous les bean utilisés ou sous utilisés par EmployeeControler
	    @MockBean
	    private EmployeeService employeeService;
	    
	    @MockBean
		private CustomProperties custumProperties ;
	    @MockBean
		private EmployeeProxy employeeProxy ;
	    
	    @Test
	    public void Given_GetMappingPathSource_When_usingHomeMethod_Then_returnHomePage() throws Exception {
	    	
	    	 mockMvc.perform(get("/"))
	            .andExpect(status().isOk());
	    }
	    
	    @Test
	    public void Given_GetMappingPathSupervision_When_usingSupervisionMethod_Then_returnSupervisionPage() throws Exception {
	    	
	    	 mockMvc.perform(get("/supervision"))
	            .andExpect(status().isOk());
	    }
	    
	    @Test
	    public void Given_GetMappingPathCreateEmploye_When_usingcreateEmployeeMethod_Then_returnCreationEmployePage() throws Exception {
	    	
	    	 mockMvc.perform(get("/createEmployee"))
	            .andExpect(status().isOk());
	    }
	
	    

}
