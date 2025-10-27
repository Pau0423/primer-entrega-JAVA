package Facturacion_Primera.Entrega.primer_entrega.dao;

import Facturacion_Primera.Entrega.primer_entrega.model.Cliente;
import Facturacion_Primera.Entrega.primer_entrega.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class DAOFactory {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }


    @Transactional
    public void persistirProducto(Producto p) {
        em.persist(p);
    }


    @Transactional
    public void persistirCliente(Cliente c) {
        em.persist(c);
    }
}
