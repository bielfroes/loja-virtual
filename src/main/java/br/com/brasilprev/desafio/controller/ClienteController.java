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

import br.com.brasilprev.desafio.model.dto.ClienteDTO;
import br.com.brasilprev.desafio.model.dto.ClienteRequestDTO;
import br.com.brasilprev.desafio.model.entity.Cliente;
import br.com.brasilprev.desafio.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = "Cliente")
@RequestMapping(value = "/cliente")
@RequiredArgsConstructor
@RestController
public class ClienteController {

	private final ClienteService service;

	@ApiOperation(value = "Criar um Cliente")
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> create(@RequestBody ClienteRequestDTO request) {
		return service.create(request);
	}

	@ApiOperation(value = "Alterar um Cliente pelo ID")
	@PutMapping(value = "/change", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteDTO> change(
			@RequestBody ClienteRequestDTO request,
			@RequestHeader(name = "idCliente") Long idCliente) {
		return service.change(request, idCliente);
	}

	@ApiOperation(value = "Buscar um Cliente pelo ID")
	@GetMapping(value = "/{idCliente}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClienteDTO> findById(@PathVariable String idCliente) {
		return service.findById(Long.parseLong(idCliente));
	}

	@ApiOperation(value = "Listar todos os Clientes")
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cliente>> findAll() {
		return service.findAll();
	}

	@ApiOperation(value = "Remover um Cliente pelo ID")
	@DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> remove(@ApiParam(name = "idCliente", value = "Id do Cliente") @RequestHeader(name = "idCliente") Long idCliente) {
		service.remove(idCliente);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
