package br.com.brasilprev.desafio.model.dto;

import lombok.Data;

@Data
public class ClienteRequestDTO {

    private String nome;
        
    private String email;
        
    private String senha;
    
    private String cidade;

    private String rua;

    private String bairro;

    private String cep;
    
    private String estado;
}
