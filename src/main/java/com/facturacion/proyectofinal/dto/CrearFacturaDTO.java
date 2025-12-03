package com.facturacion.proyectofinal.dto;

import lombok.Data;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Data
public class CrearFacturaDTO {

    @NotNull(message = "El clienteId es obligatorio")
    private Long clienteId;

    @NotEmpty(message = "La factura debe tener al menos un item")
    private List<Item> items;

    @Data
    public static class Item {

        @NotNull(message = "El productoId es obligatorio")
        private Long productoId;

        @Min(value = 1, message = "La cantidad debe ser al menos 1")
        private int cantidad;
    }
}
