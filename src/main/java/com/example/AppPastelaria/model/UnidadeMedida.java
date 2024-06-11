package com.example.AppPastelaria.model;

public enum UnidadeMedida {

	GRAMA ("GRAMA"),
	QUILOGRAMA ("QUILOGRAMA"),
	MILIGRAMA ("MILIGRAMA"),
	LITRO ("LITRO"),
	MILILITRO("MILILITRO");
	
	public String getName() {
		return name;
	}

	private final String name;
	
	UnidadeMedida(String name){
		this.name = name;
	}
}
