package com.example.AppPastelaria.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Pratos")
public class Pratos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private double preco;
	@Column(columnDefinition ="TEXT")
	private String descricao;
	
	
	
	@OneToMany(mappedBy = "prato_id", cascade = CascadeType.REMOVE)
	private List<ItensPrato> itens = new ArrayList<>();


	


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public double getPreco() {
		return preco;
	}


	public void setPreco(double preco) {
		this.preco = preco;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public List<ItensPrato> getItens() {
		return itens;
	}


	public void setItens(List<ItensPrato> itens) {
		this.itens = itens;
	}
	
	
}
