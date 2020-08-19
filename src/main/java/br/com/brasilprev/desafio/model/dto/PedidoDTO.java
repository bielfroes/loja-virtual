package br.com.brasilprev.desafio.model.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PedidoDTO {

    private Long idPedido;
            
    private ClienteDTO cliente;
    
    private String data;
    
    private String status;
    
    private String sessao;

}
