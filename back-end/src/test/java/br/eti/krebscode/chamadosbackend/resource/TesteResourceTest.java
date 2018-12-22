package br.eti.krebscode.chamadosbackend.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import br.eti.krebscode.chamadosbackend.BackEndApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BackEndApplication.class)
public class TesteResourceTest {

	@Autowired
	private TesteResource testeResource;
	
	private MockMvc mockMvc;
	
	@Before
    public void setup() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(testeResource)
				.build();
	}
	
	@Test
	public void getTeste() throws Exception {
		mockMvc.perform(get("/teste"))
		.andExpect(status().isOk());
	}
	
}
