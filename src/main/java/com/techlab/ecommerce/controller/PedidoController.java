package com.techlab.ecommerce.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.techlab.ecommerce.repository.PedidoRepository;
import com.techlab.ecommerce.repository.ProductoRepository;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @org.springframework.web.bind.annotation.PostMapping
    public com.techlab.ecommerce.pedidos.Pedido crear(@org.springframework.web.bind.annotation.RequestBody com.techlab.ecommerce.pedidos.Pedido pedido) {

        double totalCalculado = 0;
        for (com.techlab.ecommerce.pedidos.LineaPedido linea : pedido.getItems()) {

            com.techlab.ecommerce.productos.Producto productoReal = productoRepository.findById(linea.getProductoId()).get();

            double subtotal = productoReal.getPrecio() * linea.getCantidad();

            totalCalculado += subtotal;

            productoReal.setStock(productoReal.getStock() - linea.getCantidad());
            productoRepository.save(productoReal);
        }

        pedido.setTotal(totalCalculado);
        pedido.setFecha(java.time.LocalDateTime.now());

        return pedidoRepository.save(pedido);
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public org.springframework.http.ResponseEntity<com.techlab.ecommerce.pedidos.Pedido> actualizar(
            @org.springframework.web.bind.annotation.PathVariable Integer id,
            @org.springframework.web.bind.annotation.RequestBody com.techlab.ecommerce.pedidos.Pedido pedidoNuevo) {

        if (!pedidoRepository.existsById(id)) {
            return org.springframework.http.ResponseEntity.notFound().build();
        }

        com.techlab.ecommerce.pedidos.Pedido pedidoExistente = pedidoRepository.findById(id).get();

        for (com.techlab.ecommerce.pedidos.LineaPedido lineaVieja : pedidoExistente.getItems()) {
            com.techlab.ecommerce.productos.Producto producto = productoRepository.findById(lineaVieja.getProductoId()).get();
            // Devolvemos la cantidad al stock
            producto.setStock(producto.getStock() + lineaVieja.getCantidad());
            productoRepository.save(producto);
        }

        double nuevoTotal = 0;
        for (com.techlab.ecommerce.pedidos.LineaPedido lineaNueva : pedidoNuevo.getItems()) {
            com.techlab.ecommerce.productos.Producto producto = productoRepository.findById(lineaNueva.getProductoId()).get();

            producto.setStock(producto.getStock() - lineaNueva.getCantidad());
            productoRepository.save(producto);

            nuevoTotal += producto.getPrecio() * lineaNueva.getCantidad();
        }

        pedidoExistente.setItems(pedidoNuevo.getItems());
        pedidoExistente.setTotal(nuevoTotal);
        pedidoExistente.setFecha(java.time.LocalDateTime.now());

        return org.springframework.http.ResponseEntity.ok(pedidoRepository.save(pedidoExistente));
    }

    @org.springframework.web.bind.annotation.GetMapping

    public java.util.List<com.techlab.ecommerce.pedidos.Pedido> obtenerTodos() {

        return pedidoRepository.findAll();
    }
}
