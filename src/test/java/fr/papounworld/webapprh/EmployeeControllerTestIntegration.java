package fr.papounworld.webapprh;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

//on charge le contexte spring à chaque test
@SpringBootTest
//permet d'instancier des MockMv
@AutoConfigureMockMvc
public class EmployeeControllerTestIntegration {
	
	//on peut utiliser MockMvc grace à @AutoConfigureMockMvc
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void Given_GetMappingPathSource_When_getEmployees_ThenReturnPrenomGino() throws Exception {
		//on execute une requete de type getUrl sur "/" la page d'accueil
		//and do print permet d'afficher dans la console le test
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk()).andExpect(view().name("home")).andExpect(content().string(containsString("Gino"))) ;
		
	}

}
