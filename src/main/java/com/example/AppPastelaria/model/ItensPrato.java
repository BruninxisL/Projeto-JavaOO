package com.example.AppPastelaria.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="itens_prato")
public class ItensPrato {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="produto_id")
	private Produto produto_id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="prato_id")
	private Pratos prato_id;
	
	private int quantidadePorPrato;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto_id() {
		return produto_id;
	}

	public void setProduto_id(Produto produto_id) {
		this.produto_id = produto_id;
	}

	public Pratos getPrato_id() {
		return prato_id;
	}

	public void setPrato_id(Pratos prato_id) {
		this.prato_id = prato_id;
	}

	public int getQuantidadePorPrato() {
		return quantidadePorPrato;
	}

	public void setQuantidadePorPrato(int quantidadePorPrato) {
		this.quantidadePorPrato = quantidadePorPrato;
	}

	
	
}
