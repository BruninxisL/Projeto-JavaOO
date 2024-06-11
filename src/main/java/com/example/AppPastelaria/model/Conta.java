package com.example.AppPastelaria.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="conta")
public class Conta {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="comanda_id")
	private Comanda comanda_id;
	
	private double valorTotal;
	
	@Enumerated(EnumType.STRING)
	private TipoPagamento tipoPagamento;

    @Enumerated(EnumType.STRING)
	private StatusConta status;
    
	private LocalDateTime dataCriação;
	private LocalDateTime dataFechamento;
	
	
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
	
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public StatusConta getStatus() {
		return status;
	}

	public void setStatus(StatusConta status) {
		this.status = status;
	}

	public LocalDateTime getDataCriação() {
		return dataCriação;
	}
	public void setDataCriação(LocalDateTime dataCriação) {
		this.dataCriação = dataCriação;
	}
	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}
	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}
	
	
}
