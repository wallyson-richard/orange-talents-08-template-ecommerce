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
        if (categoriaId != null) {
            Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
            if (categoria.isPresent()) {
                return new Categoria(nome, categoria.get());
            }
        }
        return new Categoria(nome);
    }
}
