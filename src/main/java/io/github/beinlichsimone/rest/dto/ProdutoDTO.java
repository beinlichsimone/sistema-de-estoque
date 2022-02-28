package io.github.beinlichsimone.rest.dto;

import io.github.beinlichsimone.domain.enums.TipoProdutoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProdutoDTO {
    TipoProdutoEnum tipoProduto;
    BigDecimal quantidadeEstoque;
    BigDecimal quantidadeMovimentoSaida;

}
