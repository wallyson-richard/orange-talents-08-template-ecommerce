package br.com.zupacademy.wallyson.mercadolivre.auth;

import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    public JwtAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Optional<String> possibleToken = getTokenFromRequest(request);
        if (possibleToken.isPresent() && tokenService.isValid(possibleToken.get())) {
            authenticateClient(possibleToken.get());
        }
        filterChain.doFilter(request, response);
    }


    private void authenticateClient(String token) {
        Usuario usuario = tokenService.getUsuarioLogado(token);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        String tokenJwt = request.getHeader("Authorization");
        if (tokenJwt != null && tokenJwt.contains("Bearer")) {
            tokenJwt = tokenJwt.substring(7);
        }
        return Optional.ofNullable(tokenJwt);
    }
}
