package com.techlab.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.techlab.ecommerce.productos.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}