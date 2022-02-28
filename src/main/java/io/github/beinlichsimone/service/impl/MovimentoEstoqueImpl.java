package io.github.beinlichsimone.service.impl;

import io.github.beinlichsimone.domain.entity.MovimentoEstoque;
import io.github.beinlichsimone.domain.entity.Produto;
import io.github.beinlichsimone.domain.enums.TipoMovimentoEnum;
import io.github.beinlichsimone.domain.repository.MovimentoEstoqueRepository;
import io.github.beinlichsimone.domain.repository.ProdutoRepository;
import io.github.beinlichsimone.exception.EstoqueZerado;
import io.github.beinlichsimone.exception.ProdutoInvalidoException;
import io.github.beinlichsimone.rest.dto.MovimentoEstoqueDTO;
import io.github.beinlichsimone.service.MovimentoEstoqueService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovimentoEstoqueImpl implements MovimentoEstoqueService {

    private final MovimentoEstoqueRepository repository;
    private final ProdutoRepository produtosRepository;

    @Override
    @Transactional
    public MovimentoEstoque salvar(@NotNull MovimentoEstoqueDTO dto){
        Integer idProduto = dto.getProduto();
        Produto produto = produtosRepository
                .findById(idProduto)
                .orElseThrow(() -> new ProdutoInvalidoException());

        MovimentoEstoque movimentoEstoque = new MovimentoEstoque();

        String movimento = dto.getTipoMovimento();

        if ((produto.getQuantidadeEstoque().equals(0)) &&
                (TipoMovimentoEnum.valueOf(movimento).equals(TipoMovimentoEnum.valueOf("SAIDA"))) ) {
            throw new EstoqueZerado();
        }

        movimentoEstoque.setTipoMovimento(TipoMovimentoEnum.valueOf(movimento));
        movimentoEstoque.setQuantidadeMovimento(dto.getQuantidadeMovimento());
        movimentoEstoque.setValorVenda(dto.getValorVenda());
        movimentoEstoque.setDataVenda(LocalDate.now());
        movimentoEstoque.setProduto(produto);

        if (TipoMovimentoEnum.valueOf(movimento).equals(TipoMovimentoEnum.valueOf("SAIDA")))
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque().subtract(movimentoEstoque.getQuantidadeMovimento()));
        else
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque().add(movimentoEstoque.getQuantidadeMovimento()));

        produtosRepository.save(produto);

        repository.save(movimentoEstoque);
        return movimentoEstoque;

    }

    @Override
    public Optional<MovimentoEstoque> obterMovimento(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<MovimentoEstoque> findMovimento(MovimentoEstoque movimentoEstoque) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(movimentoEstoque, matcher);
        return repository.findAll(example);
    }

}
