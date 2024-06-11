package com.example.AppPastelaria.model;

public enum Status {

	RECEBIDO ("RECEBIDO"),
	PREPARANDO ("EM PREPARO"),
	CANCELADO ("CANCELADO"),
	PRONTO ("PRONTO"),
	FINALIZADO("FINALIZADO");
	
	public String getName() {
		return name;
	}

	private final String name;
	
	Status(String name){
		this.name = name;
	}
}
