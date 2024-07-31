package com.rhaissalima.restaurante.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rhaissalima.restaurante.models.Mesa;
import com.rhaissalima.restaurante.repositories.MesaRepository;

@Service
public class MesaService {

	@Autowired
	private MesaRepository mesaRepository;
	
	public Integer countClientesByMesaId(Long mesaId) {
		Optional<Mesa> mesa = mesaRepository.findById(mesaId);
		if (mesa.isPresent()) {
			if (mesa.get().getCapacidade() != null) {
				return mesa.get().getClientes().size();
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mesa não tem capacidade!", null);
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Mesa cheia!", null);
	}
	
}
