package Facturacion_Primera.Entrega.primer_entrega.repositories;

import Facturacion_Primera.Entrega.primer_entrega.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
