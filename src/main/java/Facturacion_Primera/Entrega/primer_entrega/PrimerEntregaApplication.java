package Facturacion_Primera.Entrega.primer_entrega;

import Facturacion_Primera.Entrega.primer_entrega.dao.DAOFactory;
import Facturacion_Primera.Entrega.primer_entrega.model.Cliente;
import Facturacion_Primera.Entrega.primer_entrega.model.Producto;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrimerEntregaApplication implements CommandLineRunner {

    private final DAOFactory dao;

    public PrimerEntregaApplication(DAOFactory dao) {
        this.dao = dao;
    }

    public static void main(String[] args) {
        SpringApplication.run(PrimerEntregaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        try {
            Cliente cliente1 = new Cliente("Juan Pérez", "juan@gmail.com", "+598111222");
            dao.persistirCliente(cliente1);

            Producto producto1 = new Producto("Computadora", 500.0, 10);
            dao.persistirProducto(producto1);


        } catch (Exception e) {
            e.getMessage();
        }
    }

