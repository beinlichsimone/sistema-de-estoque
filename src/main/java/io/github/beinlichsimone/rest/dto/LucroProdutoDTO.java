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
public class LucroProdutoDTO {
    Integer idProduto;
    BigDecimal valorCompra;
    BigDecimal valorVenda;
    BigDecimal quantidadeMovimentoSaida;
    BigDecimal lucroProduto;

    public LucroProdutoDTO(Integer idProduto, BigDecimal valorCompra, BigDecimal valorVenda, BigDecimal quantidadeMovimentoSaida) {
        this.idProduto = idProduto;
        this.valorCompra = valorCompra;
        this.valorVenda = valorVenda;
        this.quantidadeMovimentoSaida = quantidadeMovimentoSaida;
    }

}
