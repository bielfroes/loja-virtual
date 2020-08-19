package br.com.brasilprev.desafio.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import br.com.brasilprev.desafio.model.dto.PedidoItemDTO;
import br.com.brasilprev.desafio.model.dto.PedidoItemRequestDTO;
import br.com.brasilprev.desafio.model.entity.PedidoItem;

public interface PedidoItemService {

	ResponseEntity<PedidoItem> create(PedidoItemRequestDTO dto);

	ResponseEntity<PedidoItemDTO> change(PedidoItemRequestDTO dto, Long idPedidoItem);

	ResponseEntity<PedidoItemDTO> findById(Long idPedidoItem);

	ResponseEntity<List<PedidoItem>> findAll();

	void remove(Long idPedidoItem);
}
