package br.com.zupacademy.wallyson.mercadolivre.produto;

import br.com.zupacademy.wallyson.mercadolivre.categoria.Categoria;
import br.com.zupacademy.wallyson.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.wallyson.mercadolivre.produto.caracteristica.NovaCaracteristicaRequest;
import br.com.zupacademy.wallyson.mercadolivre.produto.imagem.ImagemProduto;
import br.com.zupacademy.wallyson.mercadolivre.produto.opiniao.Opiniao;
import br.com.zupacademy.wallyson.mercadolivre.produto.pergunta.Pergunta;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotNull
    @Positive
    private BigDecimal valor;

    @NotNull
    @PositiveOrZero
    private Long quantidade;

    @NotNull
    @Size(max = 1000)
    private String descricao;

    @NotNull
    @ManyToOne
    private Categoria categoria;

    @NotNull
    @Size(min = 3)
    @Valid
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    private Set<Caracteristica> caracteristicas;

    private LocalDateTime createdAt = LocalDateTime.now();

    @NotNull
    @ManyToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "produto", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Set<ImagemProduto> imagens;

    @OneToMany(mappedBy = "produto")
    private List<Opiniao> opinioes;

    @OneToMany(mappedBy = "produto")
    private List<Pergunta> perguntas;

    @Deprecated
    public Produto() {
    }

    public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal valor, @NotNull @PositiveOrZero Long quantidade,
                   @NotNull @Size(max = 1000) String descricao, @NotNull Categoria categoria,
                   @NotNull @Size(min = 3) @Valid Set<NovaCaracteristicaRequest> caracteristicas, @NotNull Usuario usuario) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.caracteristicas = caracteristicas.stream()
                .map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet());
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<ImagemProduto> getImagens() {
        return imagens;
    }

    public List<Opiniao> getOpinioes() {
        return opinioes;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public void adicionarImagem(Set<String> urls) {
        Set<ImagemProduto> imagens = urls.stream()
                .map(url -> new ImagemProduto(url, this))
                .collect(Collectors.toSet());

        this.imagens.addAll(imagens);
    }

    public boolean pertenceAoUsuario(Usuario usuario) {
        return this.usuario.equals(usuario);
    }

    public double calcularMediaNota() {
        var notas = todasNotas();
        var totalNotas = notas.stream().reduce(0.0, Double::sum);
        var quantidadeNotas = notas.size();

        return totalNotas == 0 || quantidadeNotas == 0 ? 0 : totalNotas / quantidadeNotas;
    }

    public List<Double> todasNotas() {
        return opinioes.stream()
                .map(Opiniao::getNota)
                .filter(Objects::nonNull)
                .map(nota -> (double) nota)
                .collect(Collectors.toList());
    }
}
