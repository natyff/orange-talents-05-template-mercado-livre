package br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.CaracteristicaRequest;


import io.jsonwebtoken.lang.Assert;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;
    @Positive
    @NotNull
    private BigDecimal valor;
    @PositiveOrZero
    private Integer quantidade;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @Valid
    @ManyToOne
    private Categoria categoria;
    @NotNull
    @Valid
    @ManyToOne
    private Usuario anunciante;
    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private Set<CaracteristicaProduto> caracteristicas = new HashSet<>();
    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<Imagem> imagens = new HashSet<>();
    @OneToMany(mappedBy = "produto")
    private List<Pergunta> perguntas = new ArrayList<>();
    @OneToMany(mappedBy = "produto")
    private List<OpiniaoProduto> opiniaoProdutos = new ArrayList<>();

    public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao,
                   Categoria categoria, Usuario anunciante, Collection<CaracteristicaRequest> caracteristicas) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoria = categoria;
        this.anunciante = anunciante;
        this.caracteristicas.addAll(caracteristicas
                .stream().map(caracteristica -> caracteristica.toModel(this))
                .collect(Collectors.toSet()));

        Assert.isTrue(this.caracteristicas.size() >=3, "É necessário informar 3 ou mais caracteristicas");
    }

    public Produto(Imagem imagem) {
    }

    public void imagensAdd(Set<String> links){
        Set<Imagem> novasImagens = links.stream()
                .map(link -> new Imagem(this, link))
                .collect(Collectors.toSet());
        this.imagens.addAll(novasImagens);
    }

    public boolean pertenceAoUsuario(Usuario anunciante2) {
        return this.anunciante.equals(anunciante2);
    }

    public Produto(){};

    public Usuario getAnunciante() {
        return anunciante;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Set<CaracteristicaProduto> getCaracteristicas() {
        return caracteristicas;
    }

    public List<Pergunta> getPerguntas() {
        return perguntas;
    }

    public List<OpiniaoProduto> getOpiniaoProdutos() {
        return opiniaoProdutos;
    }

    public Set<Imagem> getImagens() {
        return imagens;
    }

    public Long somaNotas() {
        return this.opiniaoProdutos.stream().collect(Collectors.counting());
    }

    public Map<Integer, Long> totalNotas() {
        return this.opiniaoProdutos.stream().collect(Collectors.groupingBy(OpiniaoProduto::getNota,
                Collectors.counting()));
    }
    public Double mediaNotas(){
        return this.opiniaoProdutos.stream().mapToInt(OpiniaoProduto::getNota).average().orElse(0);
    }

    public boolean retiraEstoque(Integer quantidade) {
        if(quantidade <= this.quantidade){
            this.quantidade -= quantidade;
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", categoria=" + categoria +
                ", anunciante=" + anunciante +
                ", caracteristicas=" + caracteristicas +
                ", imagens=" + imagens +
                '}';
    }


}
