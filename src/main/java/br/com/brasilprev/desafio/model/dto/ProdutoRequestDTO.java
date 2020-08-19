package br.com.brasilprev.desafio.model.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProdutoRequestDTO {

	private Long idCategoria;

	private Integer quantidade;

	private BigDecimal preco;

	private String produto;

	private String foto;

	private String descricao;

}
