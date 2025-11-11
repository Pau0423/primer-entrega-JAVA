package Facturacion_Primera.Entrega.primer_entrega;

import Facturacion_Primera.Entrega.primer_entrega.model.*;
import Facturacion_Primera.Entrega.primer_entrega.repositories.ClienteRepository;
import Facturacion_Primera.Entrega.primer_entrega.repositories.ProductoRepository;
import Facturacion_Primera.Entrega.primer_entrega.repositories.FacturaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;

@SpringBootApplication
public class PrimerEntregaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimerEntregaApplication.class, args);
    }


    @Bean
    @Profile("dev")
    CommandLineRunner init(
            ClienteRepository clientes,
            ProductoRepository productos,
            FacturaRepository facturas
    ) {
        return args -> {

            if (!clientes.existsByEmail("paula@example.com")) {
                var paula = Cliente.builder()
                        .nombre("Paula Rodríguez")
                        .email("paula@gmail.com")
                        .telefono("099123456")
                        .build();
                paula = clientes.save(paula);

                var iphone16 = Producto.builder()
                        .nombre("Iphone16 pro max")
                        .precio(450.0)
                        .stock(20)
                        .build();
                iphone16 = productos.save(iphone16);


                var factura = Factura.builder()
                        .cliente(paula)
                        .build();

                var d1 = DetalleFactura.builder()
                        .producto(iphone16)
                        .cantidad(2)
                        .precioUnitario(new BigDecimal("450.00"))
                        .build();

                factura.addDetalle(d1);
                factura.generarCodigo();
                facturas.save(factura);
            }
        };
    }
}
