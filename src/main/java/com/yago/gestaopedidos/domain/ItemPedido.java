package com.yago.gestaopedidos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private Double precoUnitario;

    public ItemPedido() {}

    public ItemPedido(Produto produto, Integer quantidade, Pedido pedido) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco();
        this.pedido = pedido;
    }

    public Double calcularSubtotal() {
        return this.precoUnitario * this.quantidade;
    }

    public Long getId() { return id; }
    public Produto getProduto() { return produto; }
    public Pedido getPedido() { return pedido; }
    public Integer getQuantidade() { return quantidade; }
    public Double getPrecoUnitario() { return precoUnitario; }
}

