package br.com.zupacademy.wallyson.mercadolivre.novacategoria;

import br.com.zupacademy.wallyson.mercadolivre.compartilhado.AtributoUnico;
import br.com.zupacademy.wallyson.mercadolivre.compartilhado.exceptions.EntityNotFoundException;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Exist;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Unique;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class NovaCategoriaRequest {

    @NotBlank
    @Unique(AtributoUnico.CATEGORIA_NOME)
    private String nome;

    @Exist(entity = Categoria.class, optional = true)
    private Long categoriaId;

    public NovaCategoriaRequest(String nome, Long categoriaId) {
        this.nome = nome;
        this.categoriaId = categoriaId;
    }

    public Categoria toModel(CategoriaRepository categoriaRepository) {
        if (categoriaId == null) {
            return new Categoria(nome);
        }

        var categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "categoria.not-found"));

        return new Categoria(nome, categoria);
    }
}
