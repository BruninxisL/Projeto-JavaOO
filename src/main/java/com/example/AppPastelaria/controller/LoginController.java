package com.example.AppPastelaria.controller;

import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.AppPastelaria.dto.UserForm;
import com.example.AppPastelaria.model.Role;
import com.example.AppPastelaria.model.User;
import com.example.AppPastelaria.repository.RoleRepository;
import com.example.AppPastelaria.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "user/login")
public class LoginController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping
	public String login() {
		 return "login/login";
	}
	
	@GetMapping("registrarForm")
	public String registrarForm( Model model) {
		model.addAttribute("userForm", new UserForm());
		
		return "admin/usuarios/cadastrarUsuario";
	}
	
	
	@PostMapping("registrar")
	public String registrar(@Valid UserForm userForm, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "login/registro" + result;
		}
		 
		User user = userForm.converter();
		
		String senhaNaoCriptografada = user.getPassword();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password = senhaNaoCriptografada;
		String encodedPassword = encoder.encode(password);
		user.setPassword(encodedPassword);
			
		Role role = roleRepository.findByName("USER");
		user.setRoles(Arrays.asList(role));
	
			
		userRepository.save(user);
		return "login/registroSuccess.html";
	}
}
