package com.example.AppPastelaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.AppPastelaria.model.ItemComanda;

@Repository
public interface ItemComandaRepository extends JpaRepository<ItemComanda, Long>{
	@Transactional
	@Modifying
    @Query("DELETE FROM ItemComanda i WHERE i.comanda_id.id = :comandaId")
    void deleteByComandaId(@Param("comandaId") Long comandaId);
	
	@Transactional
	@Modifying
    @Query("DELETE FROM ItemComanda i WHERE i.prato_id.id = :pratoId")
    void deleteByPratoId(@Param("pratoId") Long pratoId);
}
