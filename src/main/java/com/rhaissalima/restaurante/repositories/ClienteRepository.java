package com.rhaissalima.restaurante.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.rhaissalima.restaurante.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	public List<Cliente> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);
	
}
