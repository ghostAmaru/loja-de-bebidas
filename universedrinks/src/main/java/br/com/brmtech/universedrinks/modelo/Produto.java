package br.com.brmtech.universedrinks.modelo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @Column (nullable = false)
    @NotNull
    private Integer numeroProduto;
    private String nome;
    private String descricao;
    @Column(unique = false, nullable = true)
    private Integer tipo;

//    AJUSTAR REGRA DE NEGOCIO PARA FAZER A BUSCA PELO TIPO DO PRODUTO OU PELO NUMERO DO PRODUTO SENDO QUE UM DELES OU TODOS ELES SEJAM UNICOS

    public Produto() {
    }

    public Produto(String nome, Integer numeroProduto, String descricao, Integer tipo) {
        this.nome = nome;
        this.numeroProduto = numeroProduto;
        this.descricao = descricao;
        this.tipo = tipo;
    }

    public Produto(String nome, String s) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Produto other = (Produto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
