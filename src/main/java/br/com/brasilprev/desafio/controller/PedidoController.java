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

import br.com.brasilprev.desafio.model.dto.PedidoDTO;
import br.com.brasilprev.desafio.model.dto.PedidoRequestDTO;
import br.com.brasilprev.desafio.model.entity.Pedido;
import br.com.brasilprev.desafio.service.PedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = "Pedido")
@RequestMapping(value = "/pedido")
@RequiredArgsConstructor
@RestController
public class PedidoController {

	private final PedidoService service;

	@ApiOperation(value = "Criar um Pedido")
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> create(@RequestBody PedidoRequestDTO request) {
		return service.create(request);
	}

	@ApiOperation(value = "Alterar um Pedido pelo ID")
	@PutMapping(value = "/change", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PedidoDTO> change(
			@RequestBody PedidoRequestDTO request,
			@RequestHeader(name = "idPedido") Long idPedido) {
		return service.change(request, idPedido);
	}

	@ApiOperation(value = "Buscar um Pedido pelo ID")
	@GetMapping(value = "/{idPedido}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PedidoDTO> findById(@PathVariable String idPedido) {
		return service.findById(Long.parseLong(idPedido));
	}

	@ApiOperation(value = "Listar todos os Pedidos")
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pedido>> findAll() {
		return service.findAll();
	}

	@ApiOperation(value = "Remover um Pedido pelo ID")
	@DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> remove(@ApiParam(name = "idPedido", value = "Id do Pedido") @RequestHeader(name = "idPedido") Long idPedido) {
		service.remove(idPedido);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
