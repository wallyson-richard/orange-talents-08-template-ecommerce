package br.com.zupacademy.wallyson.mercadolivre.authenticate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<AutenticacaoResponse> authenticate(@RequestBody @Valid AutenticacaoRequest request) {
        UsernamePasswordAuthenticationToken dadosLogin = request.toModel();
        try {
            var authenticate = authenticationManager.authenticate(dadosLogin);
            var token = tokenService.generateToken(authenticate);
            var response = new AutenticacaoResponse(token, "Bearer");
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
