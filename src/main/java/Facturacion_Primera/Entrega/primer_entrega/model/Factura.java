package Facturacion_Primera.Entrega.primer_entrega.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
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

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @ToString.Exclude
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    @ToString.Exclude
    private List<DetalleFactura> detalles = new ArrayList<>();

    public void addDetalle(DetalleFactura d) {
        d.setFactura(this);
        detalles.add(d);
    }

    public void removeDetalle(DetalleFactura d) {
        detalles.remove(d);
        d.setFactura(null);
    }

    public void generarCodigo() {
        String fechaStr = fecha.format(DateTimeFormatter.BASIC_ISO_DATE);
        this.codigo = String.format("FAC-%s-%06d", fechaStr, this.id != null ? this.id : 0);
    }

    @Transient
    public BigDecimal getTotal() {
        return detalles.stream()
                .map(DetalleFactura::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
