package com.facturacion.proyectofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;


    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal precio;

    @Column(nullable = false)
    private Integer stock;


    @JsonIgnore
    @OneToMany(mappedBy = "producto", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<DetalleFactura> detalles = new ArrayList<>();
}
