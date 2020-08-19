package br.com.brasilprev.desafio.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PedidoItemRequestDTO {

	private Long idProduto;

	private Long idPedido;

	private String produto;

	private BigDecimal valor;

	private BigDecimal subtotal;

	private Integer quantidade;
}
