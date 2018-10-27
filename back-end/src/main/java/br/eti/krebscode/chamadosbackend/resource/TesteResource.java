package br.eti.krebscode.chamadosbackend.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teste")
public class TesteResource {

	@GetMapping
	public String teste() {
		return "Olá Mundo!";
	}
	
}
