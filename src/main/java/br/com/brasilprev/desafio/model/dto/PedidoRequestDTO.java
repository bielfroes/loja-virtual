package br.com.brasilprev.desafio.model.dto;

import lombok.Data;

@Data
public class PedidoRequestDTO {

    private Long idCliente;
    
    private String data;
    
    private String status;
    
    private String sessao;
}
