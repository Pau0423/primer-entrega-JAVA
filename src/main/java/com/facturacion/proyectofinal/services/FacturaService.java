package com.facturacion.proyectofinal.services;

import com.facturacion.proyectofinal.dto.CrearFacturaDTO;
import com.facturacion.proyectofinal.model.Cliente;
import com.facturacion.proyectofinal.model.DetalleFactura;
import com.facturacion.proyectofinal.model.Factura;
import com.facturacion.proyectofinal.model.Producto;

import com.facturacion.proyectofinal.repositories.ClienteRepository;
import com.facturacion.proyectofinal.repositories.DetalleFacturaRepository;
import com.facturacion.proyectofinal.repositories.FacturaRepository;
import com.facturacion.proyectofinal.repositories.ProductoRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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


        Factura factura = new Factura();
        factura.setCliente(cliente);
        factura.setFecha(LocalDateTime.now());
        factura.setCodigo("FAC-" + UUID.randomUUID());
        factura.setCantidadProductos(0);
        factura.setTotal(BigDecimal.ZERO);

        facturaRepo.save(factura); // genera ID

        int cantidadTotal = 0;
        BigDecimal totalGeneral = BigDecimal.ZERO;

        for (CrearFacturaDTO.Item item : dto.getItems()) {

            Producto p = productoRepo.findById(item.getProductoId())
                    .orElseThrow(() ->
                            new EntityNotFoundException("Producto no encontrado: " + item.getProductoId()));

            if (p.getStock() < item.getCantidad()) {
                throw new IllegalArgumentException("Stock insuficiente para producto: " + p.getNombre());
            }


            p.setStock(p.getStock() - item.getCantidad());
            productoRepo.save(p);


            BigDecimal precioUnitario = new BigDecimal(p.getPrecio().toString());


            DetalleFactura detalle = new DetalleFactura(
                    p,
                    item.getCantidad(),
                    precioUnitario
            );

            detalle.setFactura(factura);
            detalleRepo.save(detalle);
            factura.addDetalle(detalle);

            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(item.getCantidad()));
            totalGeneral = totalGeneral.add(subtotal);

            cantidadTotal += item.getCantidad();
        }

        factura.setCantidadProductos(cantidadTotal);
        factura.setTotal(totalGeneral);

        return facturaRepo.save(factura);
    }


    public Factura obtener(Long id) {
        return facturaRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Factura no encontrada"));
    }
}
