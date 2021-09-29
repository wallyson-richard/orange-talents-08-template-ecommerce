package br.com.zupacademy.wallyson.mercadolivre.categoria;

import br.com.zupacademy.wallyson.mercadolivre.novacategoria.Categoria;
import br.com.zupacademy.wallyson.mercadolivre.novacategoria.CategoriaRepository;
import br.com.zupacademy.wallyson.mercadolivre.novacategoria.NovaCategoriaRequest;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.Locale;

@Transactional
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
    void cadastrarCategoriaComSucesso() throws Exception {
        var categoria = new NovaCategoriaRequest("Eletrônico", null);

        cadastrarCategoria(categoria)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void naoDeveCadastrarSemNome() throws Exception {
        var categoria = new NovaCategoriaRequest("", null);

        cadastrarCategoria(categoria)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].campo").exists());
    }


    @Test
    void cadastrarCategoriaComCategoriaMaeComSucesso() throws Exception {
        var categoriaMae = categoriaRepository.save(new Categoria("Automovel"));
        var categoria = new NovaCategoriaRequest("Carro", categoriaMae.getId());
        cadastrarCategoria(categoria)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    void cadastrarCategoriaComNomeDuplicado() throws Exception {
        var categoria = new NovaCategoriaRequest("Eletrônico", null);
        cadastrarCategoria(categoria);

        cadastrarCategoria(categoria)
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].campo").value("nome"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].mensagem").value("Esse valor já existe em nossa base de dados."));
    }

    private ResultActions cadastrarCategoria(NovaCategoriaRequest categoria) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/categorias")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(gson.toJson(categoria))
                        .locale(new Locale("pt", "BR")));
    }

}
