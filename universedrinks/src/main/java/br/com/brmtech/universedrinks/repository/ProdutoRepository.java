package br.com.brmtech.universedrinks.repository;

import br.com.brmtech.universedrinks.modelo.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Optional<Produto> findByNumeroProduto(Integer numeroProduto);
}
