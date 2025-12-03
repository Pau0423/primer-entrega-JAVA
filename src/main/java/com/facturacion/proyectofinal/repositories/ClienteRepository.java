package com.facturacion.proyectofinal.repositories;

import com.facturacion.proyectofinal.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    boolean existsByEmail(String email);
}
