package com.techlab.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @org.springframework.beans.factory.annotation.Autowired
    private com.techlab.ecommerce.repository.ProductoRepository productoRepository;

    @org.springframework.web.bind.annotation.GetMapping
    public java.util.List<com.techlab.ecommerce.productos.Producto> obtenerTodos() {
        return productoRepository.findAll();

    }

    @org.springframework.web.bind.annotation.GetMapping("/{id}")
    public java.util.Optional<com.techlab.ecommerce.productos.Producto> obtenerPorId(@org.springframework.web.bind.annotation.PathVariable Integer id) {
        return productoRepository.findById(id);
    }

    @org.springframework.web.bind.annotation.PostMapping
    public com.techlab.ecommerce.productos.Producto crear(@org.springframework.web.bind.annotation.RequestBody com.techlab.ecommerce.productos.Producto producto) {
        return productoRepository.save(producto);
    }

    @org.springframework.web.bind.annotation.DeleteMapping("/{id}")
    public org.springframework.http.ResponseEntity<Void> eliminar(@org.springframework.web.bind.annotation.PathVariable Integer id) {
        if (!productoRepository.existsById(id)) {
            return org.springframework.http.ResponseEntity.notFound().build();
        }
        productoRepository.deleteById(id);

        return org.springframework.http.ResponseEntity.noContent().build();
    }

    @org.springframework.web.bind.annotation.PutMapping("/{id}")
    public org.springframework.http.ResponseEntity<com.techlab.ecommerce.productos.Producto> actualizar(
            @org.springframework.web.bind.annotation.PathVariable Integer id,
            @org.springframework.web.bind.annotation.RequestBody com.techlab.ecommerce.productos.Producto datosNuevos) {

        if (!productoRepository.existsById(id)) {
            return org.springframework.http.ResponseEntity.notFound().build();
        }

        com.techlab.ecommerce.productos.Producto productoExistente = productoRepository.findById(id).get();

        productoExistente.setNombre(datosNuevos.getNombre());
        productoExistente.setDescripcion(datosNuevos.getDescripcion());
        productoExistente.setPrecio(datosNuevos.getPrecio());
        productoExistente.setStock(datosNuevos.getStock());
        productoExistente.setImagenUrl(datosNuevos.getImagenUrl());

        com.techlab.ecommerce.productos.Producto productoGuardado = productoRepository.save(productoExistente);
        return org.springframework.http.ResponseEntity.ok(productoGuardado);
    }
}