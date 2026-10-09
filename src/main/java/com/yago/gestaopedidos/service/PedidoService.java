package com.yago.gestaopedidos.service;

import com.yago.gestaopedidos.domain.Pedido;
import com.yago.gestaopedidos.domain.Produto;
import com.yago.gestaopedidos.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoService produtoService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
    }

    @Transactional
    public Pedido criarPedido(String cliente, Long produtoId, int quantidade) {
        // Busca o produto gerenciado pelo banco
        Produto produto = produtoService.buscarPorId(produtoId);

        // Cria o pedido aplicando a regra de negócio orientada a objetos
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(produto, quantidade);

        // Salva o pedido (o CascadeType.ALL vai salvar os itens e atualizar o estoque automaticamente)
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com o ID: " + id));
    }
}

