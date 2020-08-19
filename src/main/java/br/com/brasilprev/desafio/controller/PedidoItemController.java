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

import br.com.brasilprev.desafio.model.dto.PedidoItemDTO;
import br.com.brasilprev.desafio.model.dto.PedidoItemRequestDTO;
import br.com.brasilprev.desafio.model.entity.PedidoItem;
import br.com.brasilprev.desafio.service.PedidoItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

@Api(tags = "Pedido Item")
@RequestMapping(value = "/pedido-item")
@RequiredArgsConstructor
@RestController
public class PedidoItemController {

	private final PedidoItemService service;

	@ApiOperation(value = "Criar um Pedido Item")
	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PedidoItem> create(@RequestBody PedidoItemRequestDTO request) {
		return service.create(request);
	}

	@ApiOperation(value = "Alterar um Pedido Item pelo ID")
	@PutMapping(value = "/change", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PedidoItemDTO> change(
			@RequestBody PedidoItemRequestDTO dto,
			@RequestHeader(name = "idPedidoItem") Long idPedidoItem) {
		return service.change(dto, idPedidoItem);
	}

	@ApiOperation(value = "Buscar um Pedido Item pelo ID")
	@GetMapping(value = "/{idPedidoItem}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PedidoItemDTO> findById(@PathVariable String idPedidoItem) {
		return service.findById(Long.parseLong(idPedidoItem));
	}

	@ApiOperation(value = "Listar todos os Pedidos Itens")
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PedidoItem>> findAll() {
		return service.findAll();
	}

	@ApiOperation(value = "Remover um Pedido Item pelo ID")
	@DeleteMapping(value = "/remove", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> remove(@ApiParam(name = "idPedidoItem", value = "Id do Pedido Item") @RequestHeader(name = "idPedido") Long idPedido) {
		service.remove(idPedido);
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
