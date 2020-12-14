package br.com.brmtech.universedrinks.controller;

import br.com.brmtech.universedrinks.dto.ProdutoDto;
import br.com.brmtech.universedrinks.modelo.Produto;
import br.com.brmtech.universedrinks.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class UniverseDrinksController {
    @Autowired
    private ProdutoRepository repository;

    //    LISTAR TODOS PRODUTOS DA LOJA
    @GetMapping
    public List<ProdutoDto> listaEmOrdemCrescenteTodosProdutos(Object produtoDto) {
        List<Produto> all = repository.findAll(Sort.by(Sort.Direction.ASC,"numeroProduto"));
        List<ProdutoDto> allDto = new ArrayList<>();
        all.forEach(produto -> allDto.add(new ProdutoDto(produto)));
        return allDto;
    }

    //    RECUPERANDO PELO NUMERO DO PRODUTO
    @GetMapping("/{numeroProduto}")
    public ResponseEntity recuperaPorNumeroProduto(@PathVariable("numeroProduto") Integer numeroProduto){
        Optional<Produto> produtoDB = repository.findByNumeroProduto(numeroProduto);
        if (produtoDB.isPresent()){
            return ResponseEntity.ok(new ProdutoDto(produtoDB.get()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //    CADASTRAR UM NOVO PRODUTO
    @PostMapping
    public ResponseEntity novoProduto(@Valid @RequestBody ProdutoDto produto){
        Optional<Produto>  produtoDB = repository.findByNumeroProduto(produto.getNumeroProduto());
        if (produtoDB.isPresent()){
            return ResponseEntity.badRequest().body("Numero de produto já cadastrado!!!");
        } else {
            Produto saved = repository.save(produto.toProduto());
            return ResponseEntity.ok(new ProdutoDto(saved));
        }
    }

    //    ATUALIZANDO UM PRODUTO PELO NÚMERO DE PRODUTO
    @PostMapping("/{numeroProduto}")
    public ResponseEntity atualizar(@PathVariable("numeroProduto") Integer numeroProduto, @Valid @RequestBody ProdutoDto dadoAtualizados) {

        Optional<Produto>  produtoDB = repository.findByNumeroProduto(numeroProduto);

        if (produtoDB.isPresent()){
            Produto atualizado = produtoDB.get();
            if (dadoAtualizados.getNumeroProduto() != null && !dadoAtualizados.getNumeroProduto().equals(numeroProduto)){
                Optional<Produto>  produtoComNumeroIgual = repository.findByNumeroProduto(dadoAtualizados.getNumeroProduto());
                if (produtoComNumeroIgual.isPresent()){
                    return ResponseEntity.badRequest().body("Produto já está cadastrado com este Número!");
                } else {
                    atualizado.setNumeroProduto(dadoAtualizados.getNumeroProduto());
                }
            }
            atualizado.setNome(dadoAtualizados.getNome());
            atualizado.setDescricao(dadoAtualizados.getDescricao());

            Produto saved = repository.save(atualizado);
            return ResponseEntity.ok(new ProdutoDto(saved));
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //    DELETANDO UM PRODUTO PELO NÚMERO
    @DeleteMapping("/{numeroProduto}")
    public ResponseEntity deleteByNumeroProduto(@PathVariable("numeroProduto") Integer numeroProduto){

        Optional<Produto>  produtoDB = repository.findByNumeroProduto(numeroProduto);
        if (produtoDB.isPresent()){
            repository.delete(produtoDB.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
