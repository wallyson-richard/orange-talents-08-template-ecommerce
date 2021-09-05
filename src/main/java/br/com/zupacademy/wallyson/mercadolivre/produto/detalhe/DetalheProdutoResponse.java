package br.com.zupacademy.wallyson.mercadolivre.produto.detalhe;

import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.produto.imagem.ImagemProduto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalheProdutoResponse {

    private String nome;
    private BigDecimal preco;
    private String descricao;
    private Set<String> images;
    private Set<DetalheCaracteristicasResponse> caracteristicas;
    private List<DetalheOpiniaoResponse> opinioes;
    private List<DetalhePerguntaResponse> perguntas;
    private Double mediaNota;
    private Integer total;

    public DetalheProdutoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.images = produto.getImagens()
                .stream()
                .map(ImagemProduto::getUrl)
                .collect(Collectors.toSet());
        this.caracteristicas = produto.getCaracteristicas()
                .stream()
                .map(DetalheCaracteristicasResponse::new)
                .collect(Collectors.toSet());
        this.opinioes = produto.getOpinioes()
                .stream()
                .map(DetalheOpiniaoResponse::new)
                .collect(Collectors.toList());
        this.perguntas = produto.getPerguntas()
                .stream()
                .map(DetalhePerguntaResponse::new)
                .collect(Collectors.toList());
        this.mediaNota = produto.calcularMediaNota();
        this.total = produto.todasNotas().size();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<String> getImages() {
        return images;
    }

    public Set<DetalheCaracteristicasResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public List<DetalheOpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public List<DetalhePerguntaResponse> getPerguntas() {
        return perguntas;
    }

    public Double getMediaNota() {
        return mediaNota;
    }

    public Integer getTotal() {
        return total;
    }
}
