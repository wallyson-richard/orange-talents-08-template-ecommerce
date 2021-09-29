package br.com.zupacademy.wallyson.mercadolivre.authenticate;

import br.com.zupacademy.wallyson.mercadolivre.novousuario.Usuario;
import br.com.zupacademy.wallyson.mercadolivre.novousuario.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public String generateToken(Authentication authentication) {
        var usuario = (Usuario) authentication.getPrincipal();
        var authenticateDate = new Date();
        var expirationDate = new Date(authenticateDate.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setSubject(usuario.getId().toString())
                .setIssuedAt(authenticateDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Usuario getUsuarioLogado(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        Long usuarioId = Long.parseLong(body.getSubject());
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new UsernameNotFoundException("Dados inválidos."));
    }
}
