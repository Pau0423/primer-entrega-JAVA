
package com.facturacion.proyectofinal.controllers;

import com.facturacion.proyectofinal.model.Producto;
import com.facturacion.proyectofinal.responses.ErrorResponse;
import com.facturacion.proyectofinal.services.ProductoService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Producto p) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.crear(p));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error al crear producto", e.getMessage()));
        }
    }

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.obtener(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("No encontrado", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Producto p) {
        try {
            return ResponseEntity.ok(service.actualizar(id, p));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("No encontrado", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("No encontrado", e.getMessage()));
        }
    }
}
