package Facturacion_Primera.Entrega.primer_entrega.controllers;

import Facturacion_Primera.Entrega.primer_entrega.model.Producto;
import Facturacion_Primera.Entrega.primer_entrega.services.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto p) {
        return ResponseEntity.ok(service.crear(p));
    }

    @GetMapping
    public List<Producto> listar() { return service.listar(); }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Long id) { return service.obtener(id); }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Long id, @RequestBody Producto p) {
        return service.actualizar(id, p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
