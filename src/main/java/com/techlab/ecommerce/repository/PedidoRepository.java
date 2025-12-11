package com.techlab.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.techlab.ecommerce.pedidos.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}