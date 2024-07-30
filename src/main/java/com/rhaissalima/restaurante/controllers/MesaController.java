package com.rhaissalima.restaurante.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rhaissalima.restaurante.models.Mesa;
import com.rhaissalima.restaurante.repositories.MesaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mesas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MesaController {

	@Autowired
	private MesaRepository mesaRepository;

	@GetMapping
	public ResponseEntity<List<Mesa>> getAll() {
		return ResponseEntity.ok(mesaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Mesa> getById(@PathVariable Long id) {
		return mesaRepository.findById(id).map(m -> ResponseEntity.ok(m))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/numero-mesa/{numero}")
	public ResponseEntity<Optional<Mesa>> getByNumero(@PathVariable Integer numero) {
		return ResponseEntity.ok(mesaRepository.findByNumero(numero));
	}

	@GetMapping("/capacidade/{capacidade}")
	public ResponseEntity<List<Mesa>> getAllByCapacidade(@PathVariable Integer capacidade) {
		return ResponseEntity.ok(mesaRepository.findAllByCapacidade(capacidade));
	}

	@PostMapping
	public ResponseEntity<Mesa> post(@Valid @RequestBody Mesa mesa) {
		return ResponseEntity.status(HttpStatus.CREATED).body(mesaRepository.save(mesa));
	}

	@PutMapping
	public ResponseEntity<Mesa> put(@Valid @RequestBody Mesa mesa) {
		return mesaRepository.findById(mesa.getId())
				.map(m -> ResponseEntity.status(HttpStatus.OK).body(mesaRepository.save(mesa)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Optional<Mesa> mesa = mesaRepository.findById(id);
		if (mesa.isEmpty()) {
			new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		mesaRepository.deleteById(id);
	}

}
