package br.com.zupacademy.wallyson.mercadolivre.categoria;

import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Exist;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Unique;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class NovaCategoriaRequest {

    @NotBlank
    @Unique(entity = Categoria.class, attribute = "nome")
    private String nome;

    @Exist(entity = Categoria.class, optional = true)
    private Long categoriaId;

    public String getNome() {
        return nome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository) {
        if (categoriaId == null) {
            return new Categoria(nome);
        }
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
        return categoria.map(categoria1 -> new Categoria(nome, categoria1)).orElse(new Categoria(nome));
    }
}
