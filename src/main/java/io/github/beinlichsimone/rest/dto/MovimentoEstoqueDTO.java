package io.github.beinlichsimone.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovimentoEstoqueDTO {
    private Integer produto;
    private BigDecimal valorVenda;
    private String dataVenda;
    private BigDecimal quantidadeMovimento;
    private String tipoMovimento;

}
