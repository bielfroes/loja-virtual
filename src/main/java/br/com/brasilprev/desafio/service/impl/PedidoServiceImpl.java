package br.com.brasilprev.desafio.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.brasilprev.desafio.model.dto.PedidoDTO;
import br.com.brasilprev.desafio.model.dto.PedidoRequestDTO;
import br.com.brasilprev.desafio.model.entity.Cliente;
import br.com.brasilprev.desafio.model.entity.Pedido;
import br.com.brasilprev.desafio.repository.ClienteRepository;
import br.com.brasilprev.desafio.repository.PedidoRepository;
import br.com.brasilprev.desafio.service.PedidoService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {
	
	private final PedidoRepository repository;

	private final ClienteRepository clienteRepository;

	private final ClienteServiceImpl clienteService;

	@Override
	public ResponseEntity<Pedido> create(PedidoRequestDTO dto) {
		
		Pedido entity = repository.save(createPedidoOBJ(dto, findCliente(dto.getIdCliente()), null));
		return ResponseEntity.ok(entity);
	}

	@Override
	public ResponseEntity<PedidoDTO> change(PedidoRequestDTO dto, Long idPedido) {
		Optional<Pedido> op = repository.findById(idPedido);
        
        if (op.isPresent()) {
        	Pedido entity = repository.save(this.createPedidoOBJ(dto, findCliente(dto.getIdCliente()), idPedido));
        	return ResponseEntity.ok(createPedidoOBJ(entity));
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<PedidoDTO> findById(Long idPedido) {
		Optional<Pedido> op = repository.findById(idPedido);
		
		if (op.isPresent())
			return ResponseEntity.ok(createPedidoOBJ(op.get()));

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Pedido>> findAll() {
		Iterable<Pedido> it = repository.findAll();
		
		if (it != null) {
			List<Pedido> list = new ArrayList<>();
        	it.forEach(list::add);
			return ResponseEntity.ok(list);
		}
		
		return new ResponseEntity<>(new LinkedList<>(), HttpStatus.NO_CONTENT);
	}

	@Override
	public void remove(Long idPedido) {
		repository.deleteById(idPedido);
	}
	
	private Cliente findCliente(Long idCliente) {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		if (cliente.isPresent()) {
			return cliente.get();
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND);
	}
	
	private Pedido createPedidoOBJ(PedidoRequestDTO dto, Cliente cliente, Long idPedido) {

		return Pedido.builder()
				.idPedido(idPedido)
				.data(dto.getData())
				.sessao(dto.getSessao())
				.status(dto.getStatus())
				.idCliente(cliente)
				.build();
	}
	
	private PedidoDTO createPedidoOBJ(Pedido entity) {

		return PedidoDTO.builder()
				.data(entity.getData())
				.sessao(entity.getSessao())
				.status(entity.getStatus())
				.cliente(clienteService.createClienteDtoOBJ(entity.getIdCliente()))
				.build();
	}

}
