package Facturacion_Primera.Entrega.primer_entrega;

import Facturacion_Primera.Entrega.primer_entrega.dao.DAOFactory;
import Facturacion_Primera.Entrega.primer_entrega.model.Cliente;
import Facturacion_Primera.Entrega.primer_entrega.model.Factura;
import Facturacion_Primera.Entrega.primer_entrega.model.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrimerEntregaApplication implements CommandLineRunner {
@Autowired
private DAOFactory dao;
	public static void main(String[] args) {
		SpringApplication.run(PrimerEntregaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        try{
Cliente cliente1 =  new Cliente("Juan ");
Producto producto1 =  new Producto("Computadora", "500", 10;

        }catch(Exception err){
            err.getMessage();
        }
    }
}
