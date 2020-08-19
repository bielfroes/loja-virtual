package br.com.brasilprev.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brasilprev.desafio.model.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {}
