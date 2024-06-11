package com.example.AppPastelaria.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.AppPastelaria.dto.UserForm;
import com.example.AppPastelaria.model.Role;
import com.example.AppPastelaria.model.User;
import com.example.AppPastelaria.repository.RoleRepository;
import com.example.AppPastelaria.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping(value="admin")
public class AdminUserController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	

	@GetMapping("/usuarios")
	public String usersLista(Model model, Principal principal) {
		
		String username = principal.getName();
		model.addAttribute("username", username);
		
		User user = userRepository.findByEmail(username);
		model.addAttribute("user", user);
		
		List<User> usuarios = userRepository.findAll();
		model.addAttribute("usuarios", usuarios);
		return "admin/usuarios/listarUsuarios";
	}
	
	
	@GetMapping("/registrarForm")
	public String registrarForm(UserForm userForm){		
		
		 return "admin/usuarios/cadastrarUsuarios";
	}
	
	@GetMapping("/editar/{id}")
	public String formEditUser(@PathVariable Long id,Model model) {
		User usuario = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		model.addAttribute("usuario",usuario);
		return"admin/usuarios/editarUsuario";
	}
	
	
	@PostMapping("/registrar")
	public String registrar(@Valid UserForm userForm, BindingResult result, Model model) {
		
		
		if(result.hasErrors()) {
			return "admin/usuarios/cadastrarUsuarios";
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
		return "admin/usuarios/registroSuccess.html";
	}
	
	@PostMapping("/editarUser/{id}")
	public String editUser(@PathVariable Long id, @ModelAttribute User userAtualizado, BindingResult result) {
		if(result.hasErrors()) {
			return"admin/editarUsuario";
		}
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
		user.setNome(userAtualizado.getNome());
		user.setSobrenome(userAtualizado.getSobrenome());
		user.setEmail(userAtualizado.getEmail());
		user.setCpf(userAtualizado.getCpf());
		user.setAtivo(userAtualizado.isAtivo());
		
		String novaSenha = userAtualizado.getPassword();
		
		if (!novaSenha.equals(user.getPassword())) {
		       BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		       String encodedPassword = encoder.encode(novaSenha);
		       user.setPassword(encodedPassword);
		 }
		
		
		userRepository.save(user);
		
		return "admin/usuarios/listarUsuarios";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluirUsuario(@PathVariable Long id) {
	    User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));
	    user.setAtivo(false); 
	    userRepository.save(user);
	    return "redirect:/admin/usuarios"; 
	}

}
