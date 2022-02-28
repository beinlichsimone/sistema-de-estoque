package io.github.beinlichsimone.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.beinlichsimone.domain.enums.TipoMovimentoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovimentoEstoque {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Enumerated(EnumType.ORDINAL)
    private TipoMovimentoEnum tipoMovimento;

    private BigDecimal valorVenda;

    private LocalDate dataVenda;

    private BigDecimal quantidadeMovimento;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @JsonIgnore
    public Produto getProduto() {
        return produto;
    }
}
