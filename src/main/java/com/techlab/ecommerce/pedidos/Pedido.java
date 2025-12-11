package com.techlab.ecommerce.pedidos;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedidos")

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int usuarioId;
    private double total;
    private LocalDateTime fecha;

    @jakarta.persistence.OneToMany(cascade = jakarta.persistence.CascadeType.ALL)
    private List<LineaPedido> items;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public List<LineaPedido> getItems() {
        return items;
    }

    public void setItems(List<LineaPedido> items) {
        this.items = items;
    }
}