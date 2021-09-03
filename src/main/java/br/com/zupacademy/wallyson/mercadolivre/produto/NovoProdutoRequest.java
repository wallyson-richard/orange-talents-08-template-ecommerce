package br.com.zupacademy.wallyson.mercadolivre.produto;

import br.com.zupacademy.wallyson.mercadolivre.categoria.Categoria;
import br.com.zupacademy.wallyson.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.wallyson.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.wallyson.mercadolivre.produto.caracteristica.NovaCaracteristicaRequest;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Exist;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

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

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Long getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Set<NovaCaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Produto toModel(CategoriaRepository categoriaRepository, Usuario usuario) {
        Set<Caracteristica> caracteristicaList = caracteristicas.stream()
                .map(NovaCaracteristicaRequest::toModel)
                .collect(Collectors.toSet());

        return categoriaRepository.findById(categoriaId)
                .map(categoria -> new Produto(nome, valor, quantidade, descricao, categoria, caracteristicaList, usuario))
                .orElseThrow(() -> new IllegalArgumentException("Informe uma categoria válida."));
    }
}
