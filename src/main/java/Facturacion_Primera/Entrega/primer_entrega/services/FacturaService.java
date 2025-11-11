package Facturacion_Primera.Entrega.primer_entrega.services;

import Facturacion_Primera.Entrega.primer_entrega.dto.CrearFacturaDTO;
import Facturacion_Primera.Entrega.primer_entrega.model.*;
import Facturacion_Primera.Entrega.primer_entrega.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class FacturaService {

    private final FacturaRepository facturaRepo;
    private final ClienteRepository clienteRepo;
    private final ProductoRepository productoRepo;
    private final DetalleFacturaRepository detalleRepo;

    public Factura crear(CrearFacturaDTO dto) {
        Cliente cliente = clienteRepo.findById(dto.getClienteId())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        Factura f = new Factura();
        f.setCliente(cliente);
        f.setFecha(LocalDateTime.now());

        facturaRepo.save(f); // genera ID

        dto.getItems().forEach(i -> {
            Producto p = productoRepo.findById(i.getProductoId())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado: " + i.getProductoId()));

            if (p.getStock() < i.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para producto " + p.getNombre());
            }

            // descuenta stock
            p.setStock(p.getStock() - i.getCantidad());
            productoRepo.save(p);

            DetalleFactura d = new DetalleFactura();
            d.setFactura(f);
            d.setProducto(p);
            d.setCantidad(i.getCantidad());
            d.setPrecioUnitario(p.getPrecio());
            detalleRepo.save(d);

            f.addDetalle(d);
        });

        // genera código con ID + fecha
        f.generarCodigo();
        return facturaRepo.save(f);
    }

    @Transactional(readOnly = true)
    public Factura obtener(Long id) {
        return facturaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada"));
    }
}
