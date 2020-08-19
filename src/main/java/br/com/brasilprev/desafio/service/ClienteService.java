package br.com.brasilprev.desafio.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.brasilprev.desafio.model.dto.ClienteDTO;
import br.com.brasilprev.desafio.model.dto.ClienteRequestDTO;
import br.com.brasilprev.desafio.model.entity.Cliente;

public interface ClienteService {

	ResponseEntity<Cliente> create(ClienteRequestDTO dto);

	ResponseEntity<ClienteDTO> change(ClienteRequestDTO dto, Long idCliente);

	ResponseEntity<ClienteDTO> findById(Long idCliente);

	ResponseEntity<List<Cliente>> findAll();

	void remove(Long idCliente);
}
