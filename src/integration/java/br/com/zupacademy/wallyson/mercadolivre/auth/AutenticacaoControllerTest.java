package br.com.zupacademy.wallyson.mercadolivre.auth;

import br.com.zupacademy.wallyson.mercadolivre.usuario.NovoUsuarioRequest;
import br.com.zupacademy.wallyson.mercadolivre.usuario.UsuarioRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class AutenticacaoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    Gson gson = new Gson();

    @BeforeEach
    void setup() {
        usuarioRepository.deleteAll();
    }

    @Test
    public void autenticarUsuario() throws Exception {
        var usuarioRequest = new NovoUsuarioRequest("wallyson@email.com", "123456");
        mockMvc.perform(post("/usuarios")
                        .content(gson.toJson(usuarioRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        var autenticacaoRequest = new AutenticacaoRequest("wallyson@email.com", "123456");
        mockMvc.perform(post("/auth")
                        .content(gson.toJson(autenticacaoRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("token").exists())
                .andExpect(jsonPath("tipo").exists())
                .andExpect(status().isOk());
    }

    @Test
    public void naoAutenticarUsuarioComDadosIncorretos() throws Exception {
        var usuarioRequest = new NovoUsuarioRequest("wallyson@email.com", "123456");
        mockMvc.perform(post("/usuarios")
                        .content(gson.toJson(usuarioRequest))
                        .contentType(MediaType.APPLICATION_JSON));

        var autenticacaoRequest = new AutenticacaoRequest("wallyson@email.com", "1234567");
        mockMvc.perform(post("/auth")
                        .content(gson.toJson(autenticacaoRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void conferirTokenValido() throws Exception {
        var usuarioRequest = new NovoUsuarioRequest("wallyson@email.com", "123456");

        mockMvc.perform(post("/usuarios")
                        .content(gson.toJson(usuarioRequest))
                        .contentType(MediaType.APPLICATION_JSON));

        var autenticacaoRequest = new AutenticacaoRequest("wallyson@email.com", "123456");

        var mvcResult = mockMvc.perform(post("/auth")
                        .content(gson.toJson(autenticacaoRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var json = mvcResult.getResponse().getContentAsString();
        var body = gson.fromJson(json, AutenticacaoResponse.class);
        var bearer = String.format("%s %s",body.getTipo(), body.getToken());
        mockMvc.perform(post("/auth")
                        .content(gson.toJson(autenticacaoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, bearer))
                .andExpect(status().isOk());

    }

    @Test
    public void conferirTokenInvalido() throws Exception {
        var usuarioRequest = new NovoUsuarioRequest("wallyson@email.com", "123456");

        mockMvc.perform(post("/usuarios")
                .content(gson.toJson(usuarioRequest))
                .contentType(MediaType.APPLICATION_JSON));

        var autenticacaoRequest = new AutenticacaoRequest("wallyson@email.com", "123456");

        var mvcResult = mockMvc.perform(post("/auth")
                        .content(gson.toJson(autenticacaoRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        var json = mvcResult.getResponse().getContentAsString();
        var body = gson.fromJson(json, AutenticacaoResponse.class);
        var bearer = String.format("%s %s1",body.getTipo(), body.getToken());
        mockMvc.perform(post("/auth")
                        .content(gson.toJson(autenticacaoRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .header(HttpHeaders.AUTHORIZATION, bearer))
                .andExpect(status().isOk());

    }

}
