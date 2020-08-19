package br.com.brasilprev.desafio.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.brasilprev.desafio.model.entity.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {}
