package com.example.AppPastelaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.AppPastelaria.model.Role;

@Repository
public interface RoleRepository  extends JpaRepository<Role, Long>{
	Role findByName(String string);
}
