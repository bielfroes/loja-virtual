package br.com.brasilprev.desafio.model.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "pedido", schema = "SCHEMA_BRASILPREV")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pedido implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE) 
    @Column(name = "idPedido", updatable = false, nullable = false)
    private Long idPedido;
            
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.DETACH)
    @JoinColumn(name="idCliente")
    @Fetch(FetchMode.SELECT)
    private Cliente idCliente;
    
    @Column(name = "data")
    private String data;
    
    @Column(name = "status")
    private String status;
    
    @Column(name = "sessao")
    private String sessao;
}
