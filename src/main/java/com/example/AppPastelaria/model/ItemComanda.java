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
@Table(name="iten_comanda")
public class ItemComanda {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="comanda_id")
	private Comanda comanda_id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name="prato_id")
	private Pratos prato_id;
	
	private int quantidade;
	private double precoUnitario;
	private String observacoes;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Comanda getComanda_id() {
		return comanda_id;
	}
	public void setComanda_id(Comanda comanda_id) {
		this.comanda_id = comanda_id;
	}
	public Pratos getPrato_id() {
		return prato_id;
	}
	public void setPrato_id(Pratos prato_id) {
		this.prato_id = prato_id;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getPrecoUnitario() {
		return precoUnitario;
	}
	public void setPrecoUnitario(double precoUnitario) {
		this.precoUnitario = precoUnitario;
	}
	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	
}
