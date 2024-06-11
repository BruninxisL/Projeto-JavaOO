package com.example.AppPastelaria.dto;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.Length;

import com.example.AppPastelaria.model.Produto;

import com.example.AppPastelaria.model.UnidadeMedida;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoForm {
	
	@NotBlank
	@Length(min = 2, max=30)
	private String nome;
	
	@DecimalMin(value = "0.01", message = "O preço mínimo deve ser 0.01")
	@DecimalMax(value = "999999.99", message = "O preço máximo permitido é 999999.99")
	private double preco_custo;
	
	@NotNull
	@Min(value = 1, message = "A quantidade mínima deve ser 1")
	private int quantidade;
	
	@NotNull
	private UnidadeMedida unidadeMedida;
	
	
	private LocalDateTime dataCadastro;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco_custo() {
		return preco_custo;
	}

	public void setPreco_custo(double preco_custo) {
		this.preco_custo = preco_custo;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
		
	}
	
	
	public Produto converter() {
		Produto produto = new Produto();
		produto.setNome(this.nome);
		produto.setPreco_custo(this.preco_custo);
		produto.setQuantidade(this.quantidade);
		produto.setUnidadeMedida(this.unidadeMedida);
		produto.setDataCadastro(LocalDateTime.now());
		return produto;
	}
}
