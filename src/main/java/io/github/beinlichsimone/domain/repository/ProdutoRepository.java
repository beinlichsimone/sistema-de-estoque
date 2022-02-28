package io.github.beinlichsimone.domain.repository;

import io.github.beinlichsimone.domain.entity.Produto;
import io.github.beinlichsimone.domain.enums.TipoProdutoEnum;
import io.github.beinlichsimone.rest.dto.LucroProdutoDTO;
import io.github.beinlichsimone.rest.dto.ProdutoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query(value = " select new io.github.beinlichsimone.rest.dto.ProdutoDTO "
                 + "(p.tipoProduto as tipoProduto, p.quantidadeEstoque as quantidadeEstoque, sum(m.quantidadeMovimento) as quantidadeMovimentoSaida) "
                 + "from Produto p left join MovimentoEstoque m on m.produto = p.id  "
                 + "where p.tipoProduto = :tipo and m.tipoMovimento = 1 group by p.tipoProduto, p.quantidadeEstoque")
    List<ProdutoDTO> consultaTipoProduto(@Param("tipo") TipoProdutoEnum tipo);

    @Query(value = " select new io.github.beinlichsimone.rest.dto.ProdutoDTO "
            + "(p.tipoProduto as tipoProduto, p.quantidadeEstoque as quantidadeEstoque, sum(m.quantidadeMovimento) as quantidadeMovimentoSaida) "
            + "from Produto p left join MovimentoEstoque m on m.produto = p.id  "
            + "where m.tipoMovimento = 1 group by p.tipoProduto, p.quantidadeEstoque")
    List<ProdutoDTO> consultaTipoProdutoTodos();


    @Query(value = " select new io.github.beinlichsimone.rest.dto.LucroProdutoDTO "
            + " (p.id, sum(p.valorCompra) as valorCompra, sum(m.valorVenda) as valorVenda, sum(m.quantidadeMovimento) as quantidadeMovimentoSaida) "
            + "from Produto p left join MovimentoEstoque m on m.produto = p.id  "
            + "where p.id = :id and m.tipoMovimento = 1 group by p.id")
    List<LucroProdutoDTO> consultaLucroPorProduto(@Param("id") Integer id);

    @Query(value = " select new io.github.beinlichsimone.rest.dto.LucroProdutoDTO "
            + " (p.id, sum(p.valorCompra) as valorCompra, sum(m.valorVenda) as valorVenda, sum(m.quantidadeMovimento) as quantidadeMovimentoSaida) "
            + "from Produto p left join MovimentoEstoque m on m.produto = p.id  "
            + "where m.tipoMovimento = 1 group by p.id")
    List<LucroProdutoDTO> consultaLucroPorProdutoTodos();
}
