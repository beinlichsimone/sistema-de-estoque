package io.github.beinlichsimone.rest.controller;

import io.github.beinlichsimone.domain.entity.MovimentoEstoque;
import io.github.beinlichsimone.domain.entity.Produto;
import io.github.beinlichsimone.domain.repository.MovimentoEstoqueRepository;
import io.github.beinlichsimone.domain.repository.ProdutoRepository;
import io.github.beinlichsimone.rest.dto.MovimentoEstoqueDTO;
import io.github.beinlichsimone.service.MovimentoEstoqueService;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/movimentoEstoque")
public class MovimentoEstoqueController {

    private MovimentoEstoqueService service;

    public MovimentoEstoqueController(MovimentoEstoqueService service) { this.service = service; }

    private MovimentoEstoqueRepository repository;


    @PostMapping
    @ResponseStatus(CREATED)
    public MovimentoEstoqueDTO save( @RequestBody MovimentoEstoqueDTO dto){
        MovimentoEstoque movimentoEstoque = service.salvar(dto);
        return dto;
    }

    @GetMapping("{id}")
    public MovimentoEstoqueDTO getById(@PathVariable Integer id ){
        return service
                .obterMovimento(id)
                .map( mov -> converter(mov) )
                .orElseThrow(() ->
                        new ResponseStatusException(NOT_FOUND, "Movimento não encontrado."));
    }

    @GetMapping
    public List <MovimentoEstoqueDTO> find(MovimentoEstoque filtro ){
        List <MovimentoEstoque> movimentos = service.findMovimento(filtro);

        if(CollectionUtils.isEmpty(movimentos)){
            return Collections.emptyList();
        }

        return movimentos.stream().map(
                movimento -> MovimentoEstoqueDTO
                        .builder()
                        .quantidadeMovimento(movimento.getQuantidadeMovimento())
                        .produto(movimento.getProduto().getId())
                        .valorVenda(movimento.getValorVenda())
                        .dataVenda(movimento.getDataVenda().toString())
                        .tipoMovimento(movimento.getTipoMovimento().name())
                        .build()
        ).collect(Collectors.toList());
    }

    private MovimentoEstoqueDTO converter( MovimentoEstoque movimentoEstoque){
        return MovimentoEstoqueDTO
                .builder()
                .valorVenda(movimentoEstoque.getValorVenda())
                .dataVenda(movimentoEstoque.getDataVenda().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .quantidadeMovimento(movimentoEstoque.getQuantidadeMovimento())
                .tipoMovimento(movimentoEstoque.getTipoMovimento().name())
                .build();
    }
}
