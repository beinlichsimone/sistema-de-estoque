package io.github.beinlichsimone.service;

import io.github.beinlichsimone.domain.entity.MovimentoEstoque;
import io.github.beinlichsimone.rest.dto.MovimentoEstoqueDTO;

import java.util.List;
import java.util.Optional;

public interface MovimentoEstoqueService {
    MovimentoEstoque salvar(MovimentoEstoqueDTO dto );
    Optional<MovimentoEstoque> obterMovimento(Integer produto_id);
    List <MovimentoEstoque> findMovimento(MovimentoEstoque movimentoEstoque);

}
