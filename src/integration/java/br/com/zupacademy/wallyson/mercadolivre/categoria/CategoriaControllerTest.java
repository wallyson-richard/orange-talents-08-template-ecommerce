package br.com.zupacademy.wallyson.mercadolivre.categoria;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private final Gson gson = new Gson();

    @BeforeEach
    void setup() {
        categoriaRepository.deleteAll();
    }

    @Test
    public void cadastrarCategoriaComSucesso() throws Exception {
        var categoria = new NovaCategoriaRequest("Eletrônico", null);
        mockMvc.perform(MockMvcRequestBuilders.post("/categorias").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(categoria)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void cadastrarCategoriaComCategoriaMaeComSucesso() throws Exception {
        var categoriaMae = categoriaRepository.save(new Categoria("Automovel"));
        var categoria = new NovaCategoriaRequest("Carro", categoriaMae.getId());
        mockMvc.perform(MockMvcRequestBuilders.post("/categorias").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(categoria)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void cadastrarCategoriaComNomeDuplicado() throws Exception {
        var categoria = new NovaCategoriaRequest("Eletrônico", null);
        mockMvc.perform(MockMvcRequestBuilders.post("/categorias").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(categoria)));

        mockMvc.perform(MockMvcRequestBuilders.post("/categorias").contentType(MediaType.APPLICATION_JSON).content(gson.toJson(categoria)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].campo").value("nome"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mensagem").value("Esse valor já existe em nossa base de dados."));
    }


}
