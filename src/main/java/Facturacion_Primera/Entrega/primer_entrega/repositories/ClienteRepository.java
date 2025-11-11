package Facturacion_Primera.Entrega.primer_entrega.repositories;

import Facturacion_Primera.Entrega.primer_entrega.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    boolean existsByEmail(String email);
}
