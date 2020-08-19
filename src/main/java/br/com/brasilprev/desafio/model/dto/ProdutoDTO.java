package br.com.brasilprev.desafio.model.dto;

import java.math.BigDecimal;

import br.com.brasilprev.desafio.model.entity.Categoria;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProdutoDTO {

    private Long idProduto;

    private Categoria idCategoria;
    
    private Integer quantidade;
    
    private BigDecimal preco;

    private String produto;
    
    private String foto;

    private String descricao;
}
