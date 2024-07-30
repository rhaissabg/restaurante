package com.rhaissalima.restaurante.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "O atributo nome é obrigatório!")
	@Size(min = 2, max = 50, message = "O atributo nome deve ter, ao menos, 2 caracteres!")
	private String nome;
	
	@NotNull(message = "O atributo telefone é obrigatório!")
	@Size(min = 10, max = 17, message = "O atributo nome deve ter, ao menos, 2 caracteres!")
	private String telefone;
	
	@NotNull(message = "O atributo email é obrigatório!")
	@Size(min = 6, max = 255, message = "O atributo nome deve ter, ao menos, 2 caracteres!")
	private String email;
	
	@ManyToOne
	@JsonIgnoreProperties("cliente")
	private Mesa mesa;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
}
