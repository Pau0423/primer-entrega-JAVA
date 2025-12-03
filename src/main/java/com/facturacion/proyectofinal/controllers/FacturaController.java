package com.facturacion.proyectofinal.controllers;

import com.facturacion.proyectofinal.dto.CrearFacturaDTO;
import com.facturacion.proyectofinal.model.Factura;
import com.facturacion.proyectofinal.responses.ErrorResponse;
import com.facturacion.proyectofinal.services.FacturaService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comprobantes")
public class FacturaController {

    private final FacturaService service;

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CrearFacturaDTO dto) {
        try {
            Factura factura = service.crear(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(factura);

        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Error en los datos enviados", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(
                    new ErrorResponse("Error inesperado", e.getMessage())
            );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.obtener(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Factura no encontrada", e.getMessage()));
        }
    }
}
