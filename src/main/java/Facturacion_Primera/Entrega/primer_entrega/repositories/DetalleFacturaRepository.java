package Facturacion_Primera.Entrega.primer_entrega.repositories;

import Facturacion_Primera.Entrega.primer_entrega.model.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> { }
