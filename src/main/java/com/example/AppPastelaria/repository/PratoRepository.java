package com.example.AppPastelaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AppPastelaria.model.Pratos;

@Repository
public interface PratoRepository extends JpaRepository<Pratos, Long>{

}
