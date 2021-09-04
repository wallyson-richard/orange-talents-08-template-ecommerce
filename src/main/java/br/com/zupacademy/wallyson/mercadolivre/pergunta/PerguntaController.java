package br.com.zupacademy.wallyson.mercadolivre.pergunta;

import br.com.zupacademy.wallyson.mercadolivre.exceptionhandler.ErrorResponse;
import br.com.zupacademy.wallyson.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class PerguntaController {

    private final ProdutoRepository produtoRepository;
    private final PerguntaRepository perguntaRepository;
    private final EmailSender emailSender;

    public PerguntaController(ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository,
                              EmailSender emailSender) {
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.emailSender = emailSender;
    }

    @PostMapping("/{id}/perguntas")
    public ResponseEntity<?> save(@PathVariable Long id, @RequestBody @Valid NovaPerguntaRequest request,
                                  @AuthenticationPrincipal Usuario usuario) {
        var produtoSaved = produtoRepository.findById(id);

        if (produtoSaved.isEmpty()) {
            var error = new ErrorResponse("produto", "Produto informado não existe");
            return ResponseEntity.badRequest().body(error);
        }

        produtoSaved.ifPresent(produto -> {
            var pergunta = request.toModel(produto, usuario);
            pergunta = perguntaRepository.save(pergunta);
            emailSender.enviar(pergunta);
        });

        return ResponseEntity.ok().build();
    }
}
