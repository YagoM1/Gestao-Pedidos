package com.yago.gestaopedidos.service;

import com.yago.gestaopedidos.domain.Pedido;
import com.yago.gestaopedidos.domain.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PedidoServiceTest {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoService produtoService;

    @Test
    public void deveCriarPedidoComSucessoEDebitarEstoque() {
        Produto produto = new Produto("Mouse Gamer", 150.0, 10);
        Produto produtoSalvo = produtoService.salvar(produto);

        Pedido pedido = pedidoService.criarPedido("Yago Miranda", produtoSalvo.getId(), 3);

        assertNotNull(pedido.getId());
        assertEquals("Yago Miranda", pedido.getCliente());
        assertEquals(450.0, pedido.getValorTotal());

        Produto produtoAtualizado = produtoService.buscarPorId(produtoSalvo.getId());
        assertEquals(7, produtoAtualizado.getQuantidadeEstoque());
    }

    @Test
    public void deveLancarExcecaoQuandoEstoqueForInsuficiente() {
        Produto produto = new Produto("Teclado Mecanico", 300.0, 2);
        Produto produtoSalvo = produtoService.salvar(produto);

        assertThrows(RuntimeException.class, () -> {
            pedidoService.criarPedido("Yago Miranda", produtoSalvo.getId(), 5);
        });
    }
}

