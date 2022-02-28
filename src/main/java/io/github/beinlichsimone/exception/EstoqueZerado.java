package io.github.beinlichsimone.exception;

public class EstoqueZerado extends RuntimeException {
    public EstoqueZerado (){
        super("Não é possível realizar a venda deste produto pois não há saldo em estoque");
    }
}
