package io.github.beinlichsimone.rest.controller;

import io.github.beinlichsimone.domain.entity.Produto;
import io.github.beinlichsimone.domain.enums.TipoProdutoEnum;
import io.github.beinlichsimone.domain.repository.ProdutoRepository;
import io.github.beinlichsimone.rest.dto.LucroProdutoDTO;
import io.github.beinlichsimone.rest.dto.ProdutoDTO;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody Produto produto ){
        return repository.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody Produto produto){
        repository
                .findById(id)
                .map( p -> {
                    produto.setId(p.getId());
                    repository.save(produto);
                    return produto;
                }).orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete( @PathVariable Integer id){
        repository
                .findById(id)
                .map( p -> {
                    repository.delete(p);
                    return Void.TYPE;
                }).orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado."));
    }

    @GetMapping("{id}")
    public Produto getById(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não encontrado."));
    }

    @GetMapping("/produtoPorTipo/{tipoProduto}")
    public List<ProdutoDTO> getByTipoProduto(@PathVariable TipoProdutoEnum tipoProduto){
        return repository
                .consultaTipoProduto(tipoProduto)
                .stream()
                .map( qtd -> ProdutoDTO
                        .builder()
                        .tipoProduto(qtd.getTipoProduto())
                        .quantidadeEstoque(qtd.getQuantidadeEstoque())
                        .quantidadeMovimentoSaida(qtd.getQuantidadeMovimentoSaida())
                        .build()).collect(Collectors.toList());
    }

    @GetMapping("/produtoPorTipo")
    public List<ProdutoDTO> getByTipoProdutoTodos(){
        return repository
                .consultaTipoProdutoTodos()
                .stream()
                .map( qtd -> ProdutoDTO
                        .builder()
                        .tipoProduto(qtd.getTipoProduto())
                        .quantidadeEstoque(qtd.getQuantidadeEstoque())
                        .quantidadeMovimentoSaida(qtd.getQuantidadeMovimentoSaida())
                        .build()).collect(Collectors.toList());
    }

    @GetMapping("/lucroPorProduto/{id}")
    public List<LucroProdutoDTO> getLucroById(@PathVariable Integer id){
        return repository
                .consultaLucroPorProduto(id)
                .stream()
                .map( qtd -> LucroProdutoDTO
                        .builder()
                        .idProduto(qtd.getIdProduto())
                        .valorCompra(qtd.getValorCompra())
                        .valorVenda(qtd.getValorVenda())
                        .quantidadeMovimentoSaida(qtd.getQuantidadeMovimentoSaida())
                        .lucroProduto(qtd.getQuantidadeMovimentoSaida().multiply(qtd.getValorVenda()).subtract
                                     (qtd.getQuantidadeMovimentoSaida().multiply(qtd.getValorCompra())))
                        .build()).collect(Collectors.toList());
    }

    @GetMapping("/lucroPorProduto")
    public List<LucroProdutoDTO> getLucroById(){
        return repository
                .consultaLucroPorProdutoTodos()
                .stream()
                .map( qtd -> LucroProdutoDTO
                        .builder()
                        .idProduto(qtd.getIdProduto())
                        .valorCompra(qtd.getValorCompra())
                        .valorVenda(qtd.getValorVenda())
                        .quantidadeMovimentoSaida(qtd.getQuantidadeMovimentoSaida())
                        .lucroProduto(qtd.getQuantidadeMovimentoSaida().multiply(qtd.getValorVenda()).subtract
                                (qtd.getQuantidadeMovimentoSaida().multiply(qtd.getValorCompra())))
                        .build()).collect(Collectors.toList());
    }

    @GetMapping
    public List<Produto> find(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return repository.findAll(example);

    }
}
