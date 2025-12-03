package com.facturacion.proyectofinal.repositories;

import com.facturacion.proyectofinal.model.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> { }
