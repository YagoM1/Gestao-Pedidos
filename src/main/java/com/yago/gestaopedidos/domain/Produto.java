package com.yago.gestaopedidos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private Integer quantidadeEstoque;

    public Produto() {}

    public Produto(String nome, Double preco, Integer quantidadeEstoque) {
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public void baixarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade para baixa deve ser maior que zero.");
        }
        if (this.quantidadeEstoque < quantidade) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + this.nome);
        }
        this.quantidadeEstoque -= quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade a adicionar deve ser maior que zero.");
        }
        this.quantidadeEstoque += quantidade;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public Double getPreco() { return preco; }
    public Integer getQuantidadeEstoque() { return quantidadeEstoque; }
}

