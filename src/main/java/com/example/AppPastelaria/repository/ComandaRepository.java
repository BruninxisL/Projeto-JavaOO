package com.example.AppPastelaria.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AppPastelaria.model.Comanda;
import com.example.AppPastelaria.model.Status;


@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long>{
	 List<Comanda> findByStatus(Status status);
}
