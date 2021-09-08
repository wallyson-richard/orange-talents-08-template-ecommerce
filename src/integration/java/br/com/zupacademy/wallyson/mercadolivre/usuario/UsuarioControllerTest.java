package br.com.zupacademy.wallyson.mercadolivre.usuario;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Locale;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UsuarioControllerTest {

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
    public void cadastrarUsuarioComDadosSucesso() throws Exception {
        var usuarioRequest = new NovoUsuarioRequest("wallyson@email.com", "123456");
        mockMvc.perform(post("/usuarios")
                        .content(gson.toJson(usuarioRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void naoCadastrarUsuarioComLoginInvalido() throws Exception {
        var usuarioRequest = new NovoUsuarioRequest("wallysonemail.com", "123456");
        mockMvc.perform(post("/usuarios")
                .content(gson.toJson(usuarioRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .locale(new Locale("pt", "BR")))
                .andExpect(jsonPath("$[0].campo").value("login"))
                .andExpect(jsonPath("$[0].mensagem").value("deve ser um endereço de e-mail bem formado"))
                .andExpect(status().isBadRequest());
    }
    @Test
    public void naoCadastrarUsuarioComSenhaInvalida() throws Exception {
        var usuarioRequest = new NovoUsuarioRequest("wallyson@email.com", "12345");
        mockMvc.perform(post("/usuarios")
                        .content(gson.toJson(usuarioRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .locale(new Locale("pt", "BR")))
                .andExpect(jsonPath("$[0].campo").value("senha"))
                .andExpect(jsonPath("$[0].mensagem").value("tamanho deve ser entre 6 e 2147483647"))
                .andExpect(status().isBadRequest());
    }

}
