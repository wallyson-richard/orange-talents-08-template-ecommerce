package br.com.zupacademy.wallyson.mercadolivre.produto.novoproduto;

import br.com.zupacademy.wallyson.mercadolivre.compartilhado.exceptions.EntityNotFoundException;
import br.com.zupacademy.wallyson.mercadolivre.novacategoria.Categoria;
import br.com.zupacademy.wallyson.mercadolivre.novacategoria.CategoriaRepository;
import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.produto.novacaracteristica.NovaCaracteristicaRequest;
import br.com.zupacademy.wallyson.mercadolivre.novousuario.Usuario;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Exist;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

public class NovoProdutoRequest {

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Long quantidade;

    @NotNull
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @Exist(entity = Categoria.class)
    private Long categoriaId;

    @NotNull
    @Size(min = 3)
    @Valid
    private Set<NovaCaracteristicaRequest> caracteristicas;

    public NovoProdutoRequest(String nome, BigDecimal valor, Long quantidade, String descricao, Long categoriaId, Set<NovaCaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas = caracteristicas;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
        return categoriaRepository.findById(categoriaId)
                .map(categoria -> new Produto(nome, valor, quantidade, descricao, categoria, caracteristicas, usuario))
                .orElseThrow(() -> new EntityNotFoundException("categoriaId", "categoria.not-found", categoriaId));
    }
}
