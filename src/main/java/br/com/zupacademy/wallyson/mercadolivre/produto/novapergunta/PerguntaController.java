package br.com.zupacademy.wallyson.mercadolivre.produto.novapergunta;

import br.com.zupacademy.wallyson.mercadolivre.novousuario.Usuario;
import br.com.zupacademy.wallyson.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.wallyson.mercadolivre.sender.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class PerguntaController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PerguntaRepository perguntaRepository;

    @Autowired
    private EmailSender emailSender;

    @PostMapping("/{id}/perguntas")
    public NovaPerguntaResponse save(@PathVariable Long id, @RequestBody @Valid NovaPerguntaRequest request,
                                  @AuthenticationPrincipal Usuario usuario) {
        var produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "produto.not-found"));

        var pergunta = request.toModel(produto, usuario);
        pergunta = perguntaRepository.save(pergunta);
        emailSender.perguntaCadastrada(pergunta);

        return pergunta.toDto();
    }
}
