package br.eti.krebscode.chamadosbackend.login;

import static org.junit.Assert.assertThat;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import br.eti.krebscode.chamadosbackend.domain.User;
import br.eti.krebscode.chamadosbackend.repository.UserRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LoginTest {

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private WebApplicationContext context;

	
	@Autowired
	private UserRepository userRepository;
	
	private MockMvc restMvc;
	
	
	private static final String USER_EMAIL = "teste@gmail.com";
	private static final String USER_NAME = "Name Test";
	private static final String USER_PASWORD = "teste";
	

	@Before
	public void setup() {
		restMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
		
		createUser();
	}
	
	@Transactional
	public void createUser() {
		// garante que não existe registros na tabela
		userRepository.deleteAll();
		
		// cria um novo usuário
		User user = new User();
		
		user.setEmail(USER_EMAIL);
		user.setName(USER_NAME);
		user.setPassword(passwordEncoder.encode(USER_PASWORD));
		
		userRepository.save(user);
		
	}

	
	@Test
	public void loginSuccessToken() throws Exception {
	
		MvcResult result = restMvc.perform(post("/login")
				.content("{\"email\":\""+USER_EMAIL+"\",\"password\":\""+USER_PASWORD+"\"}"))
				.andExpect(status().isOk())
				.andReturn();
		
		
		assertThat(result.getResponse().getContentAsString(), CoreMatchers.containsString("Bearer "));
		
	}
	
	@Test
	public void invalidLogin() throws Exception {

		restMvc.perform(post("/login")
				.content("{\"username\":\"fail\",\"password\":\"fail\"}"))
				.andExpect(status().is(401))
				.andReturn();
		
	}
}
