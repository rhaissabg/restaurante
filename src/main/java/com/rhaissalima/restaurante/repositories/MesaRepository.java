package com.rhaissalima.restaurante.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rhaissalima.restaurante.models.Mesa;

public interface MesaRepository extends JpaRepository<Mesa, Long> {

	public Optional<Mesa> findByNumero(Integer numero);

	public List<Mesa> findAllByCapacidade(Integer capacidade);
	
	@Query("SELECT COUNT(c) FROM Cliente c WHERE c.mesa.id = :mesaId")
	public Integer countClientesByMesaId(@Param("mesaId") Long mesaId);

}
