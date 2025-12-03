package com.facturacion.proyectofinal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "facturas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private LocalDateTime fecha;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    @ToString.Exclude
    private Cliente cliente;

    @Column(name = "cantidad_productos", nullable = false)
    private Integer cantidadProductos;

    @Column(nullable = false, precision = 38, scale = 2)
    private BigDecimal total;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<DetalleFactura> detalles = new ArrayList<>();

    public void addDetalle(DetalleFactura detalle) {
        detalle.setFactura(this);
        this.detalles.add(detalle);
    }

    public void generarCodigo() {
        String fechaStr = fecha.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        this.codigo = "FAC-" + fechaStr + "-" + id;
    }
}
