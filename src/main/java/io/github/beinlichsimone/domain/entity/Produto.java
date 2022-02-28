package io.github.beinlichsimone.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.beinlichsimone.domain.enums.TipoProdutoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    private String descricao;

    @Enumerated(EnumType.ORDINAL)
    private TipoProdutoEnum tipoProduto;

    private BigDecimal valorCompra;

    @NotNull
    private BigDecimal quantidadeEstoque;

    @OneToMany( mappedBy = "produto" )
    private List<MovimentoEstoque> movimentoEstoque;

    public Produto(Integer id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

}
