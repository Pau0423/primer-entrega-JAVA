package Facturacion_Primera.Entrega.primer_entrega.Interfaces;

import Facturacion_Primera.Entrega.primer_entrega.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> findAll();
    Optional<Cliente> findById(Long id);
    Cliente create(Cliente c);
    Cliente update(Long id, Cliente c);
    void delete(Long id);
    boolean existsByEmail(String email);
}
