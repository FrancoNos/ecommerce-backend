package com.techlab.ecommerce.pedidos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lineas_pedido")

public class LineaPedido{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int productoId;
    private int cantidad;

    public int getProductoId() {
        return productoId;
    }
    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
