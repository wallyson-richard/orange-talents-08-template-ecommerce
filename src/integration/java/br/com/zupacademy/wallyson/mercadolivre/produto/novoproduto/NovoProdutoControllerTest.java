package br.com.zupacademy.wallyson.mercadolivre.produto.novoproduto;

import br.com.zupacademy.wallyson.mercadolivre.auth.Autenticar;
import br.com.zupacademy.wallyson.mercadolivre.novacategoria.Categoria;
import br.com.zupacademy.wallyson.mercadolivre.novacategoria.CategoriaRepository;
import br.com.zupacademy.wallyson.mercadolivre.produto.novacaracteristica.NovaCaracteristicaRequest;
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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class NovoProdutoControllerTest {

    @Autowired
    private Autenticar autenticar;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoriaRepository categoriaRepository;

    private final Gson GSON = new Gson();

    private Categoria categoria;

    private NovaCaracteristicaRequest caracteristica1 = new NovaCaracteristicaRequest("cor", "vermelho");
    private NovaCaracteristicaRequest caracteristica2 = new NovaCaracteristicaRequest("conversível", "sim");
    private NovaCaracteristicaRequest caracteristica3 = new NovaCaracteristicaRequest("ar-condicionado", "sim");
    private Set<NovaCaracteristicaRequest> caracteristicas;

    @BeforeEach
    void setup() {
        categoria = new Categoria("Automóvel");
        categoriaRepository.save(categoria);
        caracteristica1 = new NovaCaracteristicaRequest("cor", "vermelho");
        caracteristica2 = new NovaCaracteristicaRequest("conversível", "sim");
        caracteristica3 = new NovaCaracteristicaRequest("ar-condicionado", "sim");
        caracteristicas = Set.of(caracteristica1, caracteristica2, caracteristica3);

    }

    @Test
    void deveCadastrarProduto() throws Exception {
        var produto = new NovoProdutoRequest("Fusca", BigDecimal.valueOf(100000), 10L, "Carro novo 0KM",
                categoria.getId(), caracteristicas);

        cadastrarProduto(produto)
                .andExpect(status().isOk());
    }

    @Test
    void naoDeveCadastrarProdutoSemNome() throws Exception {
        var produto = new NovoProdutoRequest(null, BigDecimal.valueOf(100000), 10L, "Carro novo 0KM",
                categoria.getId(), caracteristicas);

        cadastrarProduto(produto)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].campo").value("nome"))
                .andExpect(jsonPath("$[0].mensagem").value("não deve estar em branco"));
    }

    @Test
    void naoDeveCadastrarComValorMenorQueUm() throws Exception {
        var produto = new NovoProdutoRequest("Carro", BigDecimal.valueOf(0), 10L, "Carro novo 0KM",
                categoria.getId(), caracteristicas);

        cadastrarProduto(produto)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].campo").value("valor"))
                .andExpect(jsonPath("$[0].mensagem").value("deve ser maior que 0"));

        produto = new NovoProdutoRequest("Carro", null, 10L, "Carro novo 0KM",
                categoria.getId(), caracteristicas);

        cadastrarProduto(produto)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].campo").value("valor"))
                .andExpect(jsonPath("$[0].mensagem").value("não deve ser nulo"));
    }

    @Test
    void naoDeveCadastrarComQuantidadeNegativa() throws Exception {
        var produto = new NovoProdutoRequest("Carro", BigDecimal.valueOf(100000), -1L, "Carro novo 0KM",
                categoria.getId(), caracteristicas);

        cadastrarProduto(produto)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].campo").value("quantidade"))
                .andExpect(jsonPath("$[0].mensagem").value("deve ser maior ou igual a 0"));

        produto = new NovoProdutoRequest("Carro", BigDecimal.valueOf(100000), null, "Carro novo 0KM",
                categoria.getId(), caracteristicas);

        cadastrarProduto(produto)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].campo").value("quantidade"))
                .andExpect(jsonPath("$[0].mensagem").value("não deve ser nulo"));
    }

    @Test
    void naoDeveCadastrarProdutoComMenosDeTresCaracateristicas() throws Exception {
        var caracteristicas = Set.of(caracteristica1, caracteristica2);
        var produto = new NovoProdutoRequest("Fusca", BigDecimal.valueOf(100000), 10L, "Carro novo 0KM",
                categoria.getId(), caracteristicas);

        cadastrarProduto(produto)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].campo").value("caracteristicas"))
                .andExpect(jsonPath("$[0].mensagem").value("tamanho deve ser entre 3 e 2147483647"));
    }

    @Test
    void naoDeveCadastrarSemCategoriaValida() throws Exception {
        var produto = new NovoProdutoRequest("Fusca", BigDecimal.valueOf(100000), 10L, "Carro novo 0KM",
                null, caracteristicas);

        cadastrarProduto(produto)
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$[0].campo").exists());
    }

    private ResultActions cadastrarProduto(NovoProdutoRequest produto) throws Exception {
        var token = autenticar.getToken();
        return mockMvc.perform(MockMvcRequestBuilders.post("/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(GSON.toJson(produto))
                .header(HttpHeaders.AUTHORIZATION, token)
                .locale(new Locale("pt", "BR")));

    }
}
