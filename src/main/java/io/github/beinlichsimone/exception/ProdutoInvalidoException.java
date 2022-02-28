package io.github.beinlichsimone.exception;

public class ProdutoInvalidoException extends RuntimeException {
    public ProdutoInvalidoException (){
        super("Produto inválido");
    }
}
