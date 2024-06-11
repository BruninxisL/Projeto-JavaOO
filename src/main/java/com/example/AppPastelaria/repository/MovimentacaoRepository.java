package com.example.AppPastelaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.AppPastelaria.model.MovimentacaoEstoque;



public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEstoque, Long>{
	@Transactional
	@Modifying
    @Query("DELETE FROM MovimentacaoEstoque m WHERE m.produto_id.id = :produtoId")
    void deleteByProdutoId(@Param("produtoId") Long produtoId);
}
