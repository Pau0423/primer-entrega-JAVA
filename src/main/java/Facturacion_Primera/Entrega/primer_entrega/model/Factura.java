package Facturacion_Primera.Entrega.primer_entrega.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo; // Código único de factura

    private LocalDateTime fecha = LocalDateTime.now();

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleFactura> detalles = new ArrayList<>();

    // --- Constructores ---
    public Factura() {}

    public Factura(Cliente cliente) {
        this.cliente = cliente;
    }

    // --- Helpers ---
    public void addDetalle(DetalleFactura d) {
        d.setFactura(this);
        detalles.add(d);
    }

    public void removeDetalle(DetalleFactura d) {
        detalles.remove(d);
        d.setFactura(null);
    }


    public void generarCodigo() {
        String fechaStr = fecha.format(DateTimeFormatter.BASIC_ISO_DATE); // YYYYMMDD
        this.codigo = String.format("FAC-%s-%06d", fechaStr, this.id != null ? this.id : 0);
    }


    @Transient
    public BigDecimal getTotal() {
        return detalles.stream()
                .map(DetalleFactura::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    public Long getId() { return id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<DetalleFactura> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleFactura> detalles) { this.detalles = detalles; }
}
