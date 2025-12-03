package com.facturacion.proyectofinal.repositories;

import com.facturacion.proyectofinal.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> { }
