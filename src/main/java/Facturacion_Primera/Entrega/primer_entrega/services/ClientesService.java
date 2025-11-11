package Facturacion_Primera.Entrega.primer_entrega.services;

import Facturacion_Primera.Entrega.primer_entrega.model.Cliente;
import Facturacion_Primera.Entrega.primer_entrega.repositories.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientesService {

    private final ClienteRepository repo;

    public Cliente crear(Cliente c) {
        if (repo.existsByEmail(c.getEmail())) {
            throw new IllegalArgumentException("Ya existe un cliente con ese email");
        }
        return repo.save(c);
    }

    @Transactional(readOnly = true)
    public List<Cliente> listar() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Cliente obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
    }

    public Cliente actualizar(Long id, Cliente data) {
        Cliente c = obtener(id);
        c.setNombre(data.getNombre());
        c.setEmail(data.getEmail());
        c.setTelefono(data.getTelefono());
        return repo.save(c);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Cliente no encontrado");
        repo.deleteById(id);
    }
}
