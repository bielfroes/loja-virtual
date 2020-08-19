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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brasilprev.desafio.model.dto.ProdutoDTO;
import br.com.brasilprev.desafio.model.dto.ProdutoRequestDTO;
import br.com.brasilprev.desafio.model.entity.Produto;
import br.com.brasilprev.desafio.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = "Produto")
@RequestMapping(value = "/produto")
@RequiredArgsConstructor
@RestController
public class ProdutoController {

	private final ProdutoService service;

	@ApiOperation(value = "Criar um Produto")
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produto> create(@RequestBody ProdutoRequestDTO request) {
		return service.create(request);
	}

	@ApiOperation(value = "Alterar um Produto pelo ID")
	@PutMapping(value = "/change", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProdutoDTO> change(
			@RequestBody ProdutoRequestDTO request,
			@RequestHeader(name = "idProduto") Long idProduto) {
		return service.change(request, idProduto);
	}

	@ApiOperation(value = "Buscar um Produto pelo ID")
	@GetMapping(value = "/{idProduto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProdutoDTO> findById(@PathVariable String idProduto) {
		return service.findById(Long.parseLong(idProduto));
	}

	@ApiOperation(value = "Listar todos os Produtos")
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Produto>> findAll() {
		return service.findAll();
	}

	@ApiOperation(value = "Remover um Produto pelo ID")
	@DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> remove(@ApiParam(name = "idProduto", value = "Id do Produto") @RequestHeader(name = "idProduto") Long idProduto) {
		service.remove(idProduto);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
