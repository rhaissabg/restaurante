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

import com.rhaissalima.restaurante.models.Cliente;
import com.rhaissalima.restaurante.repositories.ClienteRepository;
import com.rhaissalima.restaurante.repositories.MesaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClienteController {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private MesaRepository mesaRepository;

	@GetMapping
	public ResponseEntity<List<Cliente>> getAll() {
		return ResponseEntity.ok(clienteRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getById(@PathVariable Long id) {
		return clienteRepository.findById(id).map(m -> ResponseEntity.ok(m))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nomes/{nome}")
	public ResponseEntity<List<Cliente>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(clienteRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Cliente> post(@Valid @RequestBody Cliente cliente) {
		if (mesaRepository.existsById(cliente.getMesa().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa mesa não existe!", null);
	
	}

	@PutMapping
	public ResponseEntity<Cliente> put(@Valid @RequestBody Cliente cliente) {
		if (clienteRepository.existsById(cliente.getId())) {
			if (mesaRepository.existsById(cliente.getMesa().getId())) {
				ResponseEntity.status(HttpStatus.OK).body(clienteRepository.save(cliente));
			}
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Essa mesa não existe!", null);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isEmpty()) {
			new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		clienteRepository.deleteById(id);
	}

}
