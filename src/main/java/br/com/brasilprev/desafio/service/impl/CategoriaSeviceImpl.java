package br.com.brasilprev.desafio.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.brasilprev.desafio.model.entity.Categoria;
import br.com.brasilprev.desafio.repository.CategoriaRepository;
import br.com.brasilprev.desafio.service.CategoriaService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CategoriaSeviceImpl implements CategoriaService {

	private final CategoriaRepository repository;
	
	@Override
	public ResponseEntity<Categoria> create(String categoria) {

        Categoria entity = repository.save(this.createCategoriaOBJ(categoria));
        
        return ResponseEntity.ok(entity);
    }
	
	@Override
	public ResponseEntity<Categoria> change(Long idCategoria, String categoria) {
        Optional<Categoria> op = repository.findById(idCategoria);
        
        if (op.isPresent()) {
        	Categoria entity = op.get();
        	entity.setCategoria(categoria);
        	entity = repository.save(entity);
        	return ResponseEntity.ok(entity);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@Override
	public ResponseEntity<Categoria> findById(Long idCategoria) {
		Optional<Categoria> op = repository.findById(idCategoria);
		
		if (op.isPresent())
			return ResponseEntity.ok(op.get());

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Categoria>> findAll() {
		Iterable<Categoria> it = repository.findAll();
		
		if (it != null) {
			List<Categoria> list = new ArrayList<>();
        	it.forEach(list::add);
			return ResponseEntity.ok(list);
		}
		
		return new ResponseEntity<>(new LinkedList<>(), HttpStatus.NO_CONTENT);
	}
	
	@Override
	public void remove(Long idCategoria) {
		repository.deleteById(idCategoria);
	}

	private Categoria createCategoriaOBJ(String categoria){
        return Categoria.builder()
                          .categoria(categoria)
                          .build();
    }
}
