package com.facturacion.proyectofinal;

import com.facturacion.proyectofinal.model.Cliente;
import com.facturacion.proyectofinal.model.Factura;
import com.facturacion.proyectofinal.model.Producto;
import com.facturacion.proyectofinal.model.DetalleFactura;
import com.facturacion.proyectofinal.repositories.ClienteRepository;
import com.facturacion.proyectofinal.repositories.ProductoRepository;
import com.facturacion.proyectofinal.repositories.FacturaRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@SpringBootApplication
public class FacturacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(FacturacionApplication.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner init(
            ClienteRepository clientes,
            ProductoRepository productos,
            FacturaRepository facturas
    ) {
        return args -> {

            if (!clientes.existsByEmail("paula@gmail.com")) {


                var paula = Cliente.builder()
                        .nombre("Paula Rodríguez")
                        .email("paula@gmail.com")
                        .telefono("099123456")
                        .build();
                paula = clientes.save(paula);


                var iphone16 = Producto.builder()
                        .nombre("iPhone 16 Pro Max")
                        .precio(new BigDecimal("450.00"))
                        .stock(20)
                        .build();
                iphone16 = productos.save(iphone16);


                var factura = new Factura();
                factura.setCliente(paula);
                factura.setFecha(LocalDateTime.now());
                factura.setCantidadProductos(0);
                factura.setTotal(BigDecimal.ZERO);


                var d1 = new DetalleFactura(
                        iphone16,
                        2,
                        new BigDecimal("450.00")
                );

                factura.addDetalle(d1);

                // Totales
                factura.setCantidadProductos(2);
                factura.setTotal(new BigDecimal("900.00"));

                // Código único
                factura.setCodigo("FAC-" + System.currentTimeMillis());

                // Guardar factura
                facturas.save(factura);
            }
        };
    }
}
