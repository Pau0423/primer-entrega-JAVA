package Facturacion_Primera.Entrega.primer_entrega.services;

import Facturacion_Primera.Entrega.primer_entrega.model.Producto;
import Facturacion_Primera.Entrega.primer_entrega.repositories.ProductoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoService {

    private final ProductoRepository repo;

    public Producto crear(Producto p) {
        return repo.save(p);
    }

    @Transactional(readOnly = true)
    public List<Producto> listar() {
        return repo.findAll();
    }

    @Transactional(readOnly = true)
    public Producto obtener(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
    }

    public Producto actualizar(Long id, Producto data) {
        Producto p = obtener(id);
        p.setNombre(data.getNombre());
        p.setPrecio(data.getPrecio());
        p.setStock(data.getStock());
        return repo.save(p);
    }

    public void eliminar(Long id) {
        if (!repo.existsById(id)) throw new EntityNotFoundException("Producto no encontrado");
        repo.deleteById(id);
    }
}
