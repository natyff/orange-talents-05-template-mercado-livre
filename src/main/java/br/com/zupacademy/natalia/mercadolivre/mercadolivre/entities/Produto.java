package br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.CaracteristicaRequest;

import io.jsonwebtoken.lang.Assert;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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
    @Positive
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
