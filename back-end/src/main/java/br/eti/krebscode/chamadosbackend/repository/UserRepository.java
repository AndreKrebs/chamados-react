package br.eti.krebscode.chamadosbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import br.eti.krebscode.chamadosbackend.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Transactional
	User findByEmail(String email);
	
}
