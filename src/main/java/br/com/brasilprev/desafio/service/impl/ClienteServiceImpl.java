package br.com.brasilprev.desafio.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.brasilprev.desafio.model.dto.ClienteDTO;
import br.com.brasilprev.desafio.model.dto.ClienteRequestDTO;
import br.com.brasilprev.desafio.model.entity.Cliente;
import br.com.brasilprev.desafio.repository.ClienteRepository;
import br.com.brasilprev.desafio.service.ClienteService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository repository;
	
	@Override
	public ResponseEntity<Cliente> create(ClienteRequestDTO dto) {
		Cliente entity = repository.save(this.createClienteOBJ(dto, null));

		return ResponseEntity.ok(entity);
	}

	@Override
	public ResponseEntity<ClienteDTO> change(ClienteRequestDTO dto, Long idCliente) {
		Optional<Cliente> op = repository.findById(idCliente);
        
        if (op.isPresent()) {
        	Cliente entity = repository.save(createClienteOBJ(dto, idCliente));
        	return ResponseEntity.ok(createClienteDtoOBJ(entity));
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<ClienteDTO> findById(Long idCliente) {
		Optional<Cliente> op = repository.findById(idCliente);
		
		if (op.isPresent())
			return ResponseEntity.ok(createClienteDtoOBJ(op.get()));

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<Cliente>> findAll() {
		Iterable<Cliente> it = repository.findAll();
		
		if (it != null) {
			List<Cliente> list = new ArrayList<>();
        	it.forEach(list::add);
			return ResponseEntity.ok(list);
		}
		
		return new ResponseEntity<>(new LinkedList<>(), HttpStatus.NO_CONTENT);
	}

	@Override
	public void remove(Long idCliente) {
		repository.deleteById(idCliente);
	}

	private Cliente createClienteOBJ(ClienteRequestDTO dto, Long idCliente) {

		return Cliente.builder()
				.idCliente(idCliente)
				.nome(dto.getNome())
				.senha(dto.getSenha())
				.email(dto.getEmail())
				.rua(dto.getRua())
				.bairro(dto.getBairro())
				.cidade(dto.getCidade())
				.estado(dto.getEstado())
				.cep(dto.getCep())
				.build();
	}
	
	public ClienteDTO createClienteDtoOBJ(Cliente cliente) {

		return ClienteDTO.builder()
				.nome(cliente.getNome())
				.senha(cliente.getSenha())
				.email(cliente.getEmail())
				.rua(cliente.getRua())
				.bairro(cliente.getBairro())
				.cidade(cliente.getCidade())
				.estado(cliente.getEstado())
				.cep(cliente.getCep())
				.idCliente(cliente.getIdCliente())
				.build();
	}

}
