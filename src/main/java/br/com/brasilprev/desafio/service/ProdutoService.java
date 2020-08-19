package br.com.brasilprev.desafio.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.brasilprev.desafio.model.dto.ProdutoDTO;
import br.com.brasilprev.desafio.model.dto.ProdutoRequestDTO;
import br.com.brasilprev.desafio.model.entity.Produto;

public interface ProdutoService {

	ResponseEntity<Produto> create(ProdutoRequestDTO dto);

	ResponseEntity<ProdutoDTO> change(ProdutoRequestDTO dto, Long idProduto);

	ResponseEntity<ProdutoDTO> findById(Long idProduto);

	ResponseEntity<List<Produto>> findAll();

	void remove(Long idProduto);
}
