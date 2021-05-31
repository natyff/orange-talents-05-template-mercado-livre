package br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.*;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao.ExistId;
import org.hibernate.validator.constraints.Length;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.*;

public class ProdutoRequest {


    @NotBlank
    private String nome;
    @NotNull
    @Positive(message = "0.00")
    private BigDecimal valor;
    @Positive
    private Integer quantidade;
    @NotBlank
    @Length(max = 1000)
    private String descricao;
    @NotNull
    @ExistId(domainClass = Categoria.class, fieldValue = "id")
    private Long categoriaId;
    @Size(min = 3)
    @Valid
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();


    public ProdutoRequest(String nome, BigDecimal valor, Integer quantidade, String descricao,
                          Long categoriaId, List<CaracteristicaRequest> caracteristicas) {

        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
        this.caracteristicas.addAll(caracteristicas);
    }

    public Produto converter(EntityManager em, Usuario anunciante){
        Categoria categoria = em.find(Categoria.class, categoriaId);
        return new Produto(nome, valor, quantidade, descricao, categoria, anunciante,
                caracteristicas);
    }

    public Set<String> mesmaCaracteristica() {
        HashSet<String> nomesIguais = new HashSet<>();
        HashSet<String> resultado = new HashSet<>();
        for(CaracteristicaRequest caract : caracteristicas){
            String nome = caract.getNome();
            if(!nomesIguais.add(nome)){
                resultado.add(nome);
            }
        }
        return resultado;
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }


    @Override
    public String toString() {
        return "ProdutoRequest{" +
                "nome='" + nome + '\'' +
                ", valor=" + valor +
                ", quantidade=" + quantidade +
                ", descricao='" + descricao + '\'' +
                ", categoriaId=" + categoriaId +
                ", caracteristicas=" + caracteristicas +
                '}';
    }
}
