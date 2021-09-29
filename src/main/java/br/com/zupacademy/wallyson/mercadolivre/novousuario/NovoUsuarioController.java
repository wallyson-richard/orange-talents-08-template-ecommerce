package br.com.zupacademy.wallyson.mercadolivre.novousuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class NovoUsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public NovoUsuarioResponse save(@RequestBody @Valid NovoUsuarioRequest request) {
        Usuario usuario = request.toModel();
        usuarioRepository.save(usuario);
        return usuario.toDto();
    }
}
