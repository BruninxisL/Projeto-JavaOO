package com.example.AppPastelaria.dto;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.example.AppPastelaria.model.Pratos;
import com.example.AppPastelaria.model.Produto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;

public class PratoForm {
	
	@NotBlank
	@Length(min = 2, max=30)
	private String nome;
	

	
	@DecimalMin(value = "0.01", message = "O preço mínimo deve ser 0.01")
	@DecimalMax(value = "999999.99", message = "O preço máximo permitido é 999999.99")
	private double preco;
	
	@NotBlank
	@Length(min = 8, max=50)
	private String descricao;
	
	private List<ItemPratoForm> ingredientes;
	
	private List<Produto> produtos;

	
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
	
	
	
	public List<ItemPratoForm> getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(List<ItemPratoForm> ingredientes) {
		this.ingredientes = ingredientes;
	}
	
	

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Pratos converter() {
		Pratos prato = new Pratos();
		prato.setNome(this.nome);
		prato.setPreco(this.preco);
		prato.setDescricao(this.descricao);
		return prato;
	}
}
