package br.com.brasilprev.desafio.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.brasilprev.desafio.model.dto.PedidoDTO;
import br.com.brasilprev.desafio.model.dto.PedidoRequestDTO;
import br.com.brasilprev.desafio.model.entity.Pedido;

public interface PedidoService {

	ResponseEntity<Pedido> create(PedidoRequestDTO dto);

	ResponseEntity<PedidoDTO> change(PedidoRequestDTO dto, Long idPedido);

	ResponseEntity<PedidoDTO> findById(Long idPedido);

	ResponseEntity<List<Pedido>> findAll();

	void remove(Long idPedido);
}
