package br.com.brasilprev.desafio.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasilprev.desafio.model.entity.Categoria;
import br.com.brasilprev.desafio.service.CategoriaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = "Categoria")
@RequestMapping(value = "/categoria")
@RequiredArgsConstructor
@RestController
public class CategoriaController {

	private final CategoriaService service;

	@ApiOperation(value = "Criar uma Categoria")
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> create(
			@ApiParam(name = "categoria", value = "Nome da Categoria") @RequestHeader(name = "categoria") String categoria) {
		return service.create(categoria);
	}

	@ApiOperation(value = "Alterar uma Categoria pelo ID")
	@PutMapping(value = "/change", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> change(
			@ApiParam(name = "idCategoria", value = "Id da Categoria") @RequestHeader(name = "idCategoria") Long idCategoria,
			@ApiParam(name = "categoria", value = "Nome da Categoria") @RequestHeader(name = "categoria") String categoria) {
		return service.change(idCategoria, categoria);
	}

	@ApiOperation(value = "Buscar uma Categoria pelo ID")
	@GetMapping(value = "/{idCategoria}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> findById(
			@PathVariable String idCategoria) {
		return service.findById(Long.parseLong(idCategoria));
	}

	@ApiOperation(value = "Listar todas as Categorias")
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Categoria>> findAll() {
		return service.findAll();
	}

	@ApiOperation(value = "Remover uma Categoria pelo ID")
	@DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> remove(
			@ApiParam(name = "idCategoria", value = "Id da Categoria") @RequestHeader(name = "idCategoria") Long idCategoria) {
		service.remove(idCategoria);
		return ResponseEntity.ok(HttpStatus.OK);
	}

}
