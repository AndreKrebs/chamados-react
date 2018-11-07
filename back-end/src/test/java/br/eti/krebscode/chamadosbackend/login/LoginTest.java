package br.eti.krebscode.chamadosbackend.login;

import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import br.eti.krebscode.chamadosbackend.BackEndApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackEndApplication.class)
public class LoginTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc restMvc;

	@Before
	public void setup() {
		restMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	@Transactional
	public void loginSuccessToken() throws Exception {

		MvcResult result = restMvc.perform(post("/login")
				.content("{\"username\":\"admin\",\"password\":\"password\"}"))
				.andExpect(status().isOk())
				.andReturn();
		
		
		assertThat(result.getResponse().getContentAsString(), CoreMatchers.containsString("Bearer "));
		
	}
	
	@Test
	@Transactional
	public void invalidLogin() throws Exception {

		restMvc.perform(post("/login")
				.content("{\"username\":\"fail\",\"password\":\"fail\"}"))
				.andExpect(status().is(401))
				.andReturn();
		
	}
}
