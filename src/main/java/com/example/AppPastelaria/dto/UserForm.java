package com.example.AppPastelaria.dto;

import org.hibernate.validator.constraints.Length;

import com.example.AppPastelaria.model.User;

import jakarta.validation.constraints.NotBlank;

public class UserForm {
	
	@NotBlank
	@Length(min = 2, max=30)
	private String nome;
	
	@NotBlank
	@Length(min = 2, max=30)
	private String sobrenome;
	
	@NotBlank
	@Length(min = 8, max=50)
	private String email;
	
	@NotBlank
	@Length(min = 11, max=11)
	private String cpf;
	
	@NotBlank
	@Length(min = 3, max=10)
	private String password;
	
	private boolean ativo;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public User converter() {
        User user = new User();
        user.setNome(this.nome);
        user.setSobrenome(this.sobrenome);
        user.setEmail(this.email);
        user.setCpf(this.cpf);
        user.setPassword(this.password);
        user.setAtivo(this.ativo);
        return user;
    }
}
