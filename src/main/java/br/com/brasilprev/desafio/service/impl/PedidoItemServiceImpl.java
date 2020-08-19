package br.com.brasilprev.desafio.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.brasilprev.desafio.model.dto.PedidoItemDTO;
import br.com.brasilprev.desafio.model.dto.PedidoItemRequestDTO;
import br.com.brasilprev.desafio.model.entity.Pedido;
import br.com.brasilprev.desafio.model.entity.PedidoItem;
import br.com.brasilprev.desafio.model.entity.Produto;
import br.com.brasilprev.desafio.repository.PedidoItemRepository;
import br.com.brasilprev.desafio.repository.PedidoRepository;
import br.com.brasilprev.desafio.repository.ProdutoRepository;
import br.com.brasilprev.desafio.service.PedidoItemService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PedidoItemServiceImpl implements PedidoItemService {

	private final PedidoRepository pedidoRepository;
	
	private final ProdutoRepository produtoRepository;
	
	private final PedidoItemRepository pedidoItemRepository;

	@Override
	public ResponseEntity<PedidoItem> create(PedidoItemRequestDTO dto) {
		
		Optional<Produto> opProduto = produtoRepository.findById(dto.getIdProduto());
        Optional<Pedido> opPedido = pedidoRepository.findById(dto.getIdPedido());
        
        if(opProduto.isPresent() && opPedido.isPresent()){
        	PedidoItem entity = pedidoItemRepository.save(criaPedidoItemOBJ(dto, opProduto.get(), opPedido.get()));
        	return ResponseEntity.ok(entity);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Override
	public ResponseEntity<PedidoItemDTO> change(PedidoItemRequestDTO dto, Long idPedidoItem) {
		Optional<PedidoItem> op = pedidoItemRepository.findById(idPedidoItem);
		
		if (op.isPresent()) {
			Optional<Produto> opProduto = produtoRepository.findById(dto.getIdProduto());
	        Optional<Pedido> opPedido = pedidoRepository.findById(dto.getIdPedido());
	        
	        if(opProduto.isPresent() && opPedido.isPresent()){
	        	PedidoItem entity = pedidoItemRepository.save(criaPedidoItemOBJ(dto, opProduto.get(), opPedido.get()));
	        	return ResponseEntity.ok(criaPedidoItemDtoOBJ(entity));
	        }
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<PedidoItemDTO> findById(Long idPedidoItem) {
		Optional<PedidoItem> op = pedidoItemRepository.findById(idPedidoItem);
		
		if (op.isPresent())
			return ResponseEntity.ok(criaPedidoItemDtoOBJ(op.get()));

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@Override
	public ResponseEntity<List<PedidoItem>> findAll() {
		Iterable<PedidoItem> it = pedidoItemRepository.findAll();
		
		if (it != null) {
			List<PedidoItem> list = new ArrayList<>();
        	it.forEach(list::add);
			return ResponseEntity.ok(list);
		}
		
		return new ResponseEntity<>(new LinkedList<>(), HttpStatus.NO_CONTENT);
	}

	@Override
	public void remove(Long idPedidoItem) {
		pedidoItemRepository.deleteById(idPedidoItem);
	}

	private PedidoItem criaPedidoItemOBJ(PedidoItemRequestDTO dto, Produto produto, Pedido pedido) {
		return PedidoItem.builder()              
                .idProduto(produto)
                .idPedido(pedido)
                .produto(dto.getProduto())
                .quantidade(dto.getQuantidade())
                .valor(dto.getValor())
                .subtotal(dto.getSubtotal())
               .build();
	}
	
	private PedidoItemDTO criaPedidoItemDtoOBJ(PedidoItem entity) {
		return PedidoItemDTO.builder()              
                .idProduto(entity.getIdProduto().getIdProduto())
                .idPedido(entity.getIdPedido().getIdPedido())
                .produto(entity.getProduto())
                .quantidade(entity.getQuantidade())
                .valor(entity.getValor())
                .subtotal(entity.getSubtotal())
               .build();
	}

}
