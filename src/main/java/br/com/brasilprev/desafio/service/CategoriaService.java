package br.com.brasilprev.desafio.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.brasilprev.desafio.model.entity.Categoria;

public interface CategoriaService {

	ResponseEntity<Categoria> create(String categoria);

	ResponseEntity<Categoria> change(Long idCategoria, String categoria);

	ResponseEntity<Categoria> findById(Long idCategoria);

	ResponseEntity<List<Categoria>> findAll();

	void remove(Long idCategoria);
}
