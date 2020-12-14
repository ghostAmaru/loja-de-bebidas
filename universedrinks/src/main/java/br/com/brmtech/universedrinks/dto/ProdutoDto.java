package br.com.brmtech.universedrinks.dto;

import br.com.brmtech.universedrinks.modelo.Produto;

import java.io.Serializable;

public class ProdutoDto implements Serializable {
    private Long id;
    private String nome;
    private Integer numeroProduto;
    private String descricao;
    private Integer tipo;

    public ProdutoDto() {
    }

    public ProdutoDto(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.numeroProduto = produto.getNumeroProduto();
        this.descricao = produto.getDescricao();
        this.tipo = produto.getTipo();
    }

    public Produto toProduto() {
        return new Produto(this.nome, this.numeroProduto, this.descricao, this.tipo);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumeroProduto() {
        return numeroProduto;
    }

    public void setNumeroProduto(Integer numeroProduto) {
        this.numeroProduto = numeroProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;

    }

}
