package Facturacion_Primera.Entrega.primer_entrega.controllers;

import Facturacion_Primera.Entrega.primer_entrega.dto.CrearFacturaDTO;
import Facturacion_Primera.Entrega.primer_entrega.model.Factura;
import Facturacion_Primera.Entrega.primer_entrega.services.FacturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facturas")
public class FacturaController {

    private final FacturaService service;

    @PostMapping
    public ResponseEntity<Factura> crear(@RequestBody CrearFacturaDTO dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping("/{id}")
    public Factura obtener(@PathVariable Long id) {
        return service.obtener(id);
    }
}
