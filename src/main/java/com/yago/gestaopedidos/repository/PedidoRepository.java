package com.yago.gestaopedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yago.gestaopedidos.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    
}
