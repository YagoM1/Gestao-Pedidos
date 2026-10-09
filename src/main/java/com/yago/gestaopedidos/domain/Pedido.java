package com.yago.gestaopedidos.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String cliente;

    @Column(nullable = false)
    private LocalDateTime dataPedido;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    @Column(nullable = false)
    private Double valorTotal = 0.0;

    public Pedido() {
        this.dataPedido = LocalDateTime.now();
        this.status = StatusPedido.PENDENTE;
    }

    public Pedido(String cliente) {
        this();
        this.cliente = cliente;
    }

    public void adicionarItem(Produto produto, int quantidade) {
        produto.baixarEstoque(quantidade);
        ItemPedido item = new ItemPedido(produto, quantidade, this);
        this.itens.add(item);
        calcularValorTotal();
    }

    private void calcularValorTotal() {
        this.valorTotal = this.itens.stream()
                .mapToDouble(ItemPedido::calcularSubtotal)
                .sum();
    }

    public Long getId() { return id; }
    public String getCliente() { return cliente; }
    public LocalDateTime getDataPedido() { return dataPedido; }
    public StatusPedido getStatus() { return status; }
    public List<ItemPedido> getItens() { return itens; }
    public Double getValorTotal() { return valorTotal; }
}

