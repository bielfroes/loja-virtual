package br.com.brasilprev.desafio.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.brasilprev.desafio.model.dto.ProdutoDTO;
import br.com.brasilprev.desafio.model.dto.ProdutoRequestDTO;
import br.com.brasilprev.desafio.model.entity.Categoria;
import br.com.brasilprev.desafio.model.entity.Produto;
import br.com.brasilprev.desafio.repository.CategoriaRepository;
import br.com.brasilprev.desafio.repository.ProdutoRepository;
import br.com.brasilprev.desafio.service.ProdutoService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {
	
	private final ProdutoRepository repository;

	private final CategoriaRepository categoriaRepository;

	@Override
	public ResponseEntity<Produto> create(ProdutoRequestDTO dto) {

		Optional<Categoria> opCategoria = categoriaRepository.findById(dto.getIdCategoria());
		
		if (opCategoria.isPresent()) {
			Produto entity = repository.save(createProdutoOBJ(dto, opCategoria.get()));
			return ResponseEntity.ok(entity);
		}
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<ProdutoDTO> change(ProdutoRequestDTO dto, Long idProduto) {
		Optional<Produto> op = repository.findById(idProduto);
        
        if (op.isPresent()) {
        	
        	Optional<Categoria> opCategoria = categoriaRepository.findById(dto.getIdCategoria());
        	
        	if (opCategoria.isPresent()) {
	        	Produto entity = repository.save(createProdutoOBJ(dto, opCategoria.get()));
	        	return ResponseEntity.ok(createProdutoOBJ(entity));
        	}
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<ProdutoDTO> findById(Long idProduto) {
		Optional<Produto> op = repository.findById(idProduto);
		
		if (op.isPresent())
			return ResponseEntity.ok(createProdutoOBJ(op.get()));

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Produto>> findAll() {
		Iterable<Produto> it = repository.findAll();
		
		if (it != null) {
			List<Produto> list = new ArrayList<>();
        	it.forEach(list::add);
			return ResponseEntity.ok(list);
		}
		
		return new ResponseEntity<>(new LinkedList<>(), HttpStatus.NO_CONTENT);
	}

	@Override
	public void remove(Long idProduto) {
		repository.deleteById(idProduto);
	}
	
	private Produto createProdutoOBJ(ProdutoRequestDTO dto, Categoria categoria) {
		return Produto.builder()              
                .idCategoria(categoria)
                .produto(dto.getProduto())
                .quantidade(dto.getQuantidade())
                .preco(dto.getPreco())
                .foto(dto.getFoto())
                .descricao(dto.getDescricao())
               .build();
	}
	

	private ProdutoDTO createProdutoOBJ(Produto entity) {
		return ProdutoDTO.builder()              
                .idCategoria(entity.getIdCategoria())
                .produto(entity.getProduto())
                .quantidade(entity.getQuantidade())
                .preco(entity.getPreco())
                .foto(entity.getFoto())
                .descricao(entity.getDescricao())
               .build();
	}

}
