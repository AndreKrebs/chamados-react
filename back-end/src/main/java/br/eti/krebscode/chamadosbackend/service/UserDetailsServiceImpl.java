package br.eti.krebscode.chamadosbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.eti.krebscode.chamadosbackend.config.UserDetailsConfig;
import br.eti.krebscode.chamadosbackend.domain.User;
import br.eti.krebscode.chamadosbackend.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);
		
		if(user == null) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserDetailsConfig(user.getId(), user.getEmail(), user.getPassword());
	}

	
	
}
