package br.com.brasilprev.desafio.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClienteDTO {

	private Long idCliente;

	private String nome;

	private String email;

	private String senha;

	private String cidade;

	private String rua;

	private String bairro;

	private String cep;

	private String estado;

}
