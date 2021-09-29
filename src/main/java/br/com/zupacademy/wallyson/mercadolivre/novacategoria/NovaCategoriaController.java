package br.com.zupacademy.wallyson.mercadolivre.novacategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class NovaCategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    public NovaCategoriaResponse save(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria categoria = request.toModel(categoriaRepository);
        categoriaRepository.save(categoria);
        return categoria.toDto();
    }
}
