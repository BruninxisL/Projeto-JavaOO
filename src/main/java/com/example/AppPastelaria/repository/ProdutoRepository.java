package com.example.AppPastelaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AppPastelaria.model.Produto;



public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	Produto findById(int id);
}
