package br.com.zupacademy.wallyson.mercadolivre.auth;

import br.com.zupacademy.wallyson.mercadolivre.authenticate.AutenticacaoRequest;
import br.com.zupacademy.wallyson.mercadolivre.authenticate.AutenticacaoResponse;
import br.com.zupacademy.wallyson.mercadolivre.novousuario.NovoUsuarioRequest;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Service
@Profile("test")
public class Autenticar {

    @Autowired
    private MockMvc mockMvc;

    private final Gson GSON = new Gson();

    public String getToken() throws Exception {
        var usuarioRequest = new NovoUsuarioRequest("wallyson@email.com", "123456");

        mockMvc.perform(post("/usuarios")
                .content(GSON.toJson(usuarioRequest))
                .contentType(MediaType.APPLICATION_JSON));

        var autenticacaoRequest = new AutenticacaoRequest("wallyson@email.com", "123456");

        var mvcResult = mockMvc.perform(post("/auth")
                        .content(GSON.toJson(autenticacaoRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var json = mvcResult.getResponse().getContentAsString();
        var body = GSON.fromJson(json, AutenticacaoResponse.class);
        return String.format("%s %s", body.getTipo(), body.getToken());

    }

}
