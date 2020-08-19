package br.com.brasilprev.desafio.model.dto;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PedidoItemDTO {

	private Long idItem;

	private Long idProduto;

	private Long idPedido;

	private String produto;

	private BigDecimal valor;

	private BigDecimal subtotal;

	private Integer quantidade;

}
