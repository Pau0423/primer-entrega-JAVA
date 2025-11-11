
package Facturacion_Primera.Entrega.primer_entrega.dto;

import lombok.Data;
import java.util.List;

@Data
public class CrearFacturaDTO {
    private Long clienteId;
    private List<Item> items;

    @Data
    public static class Item {
        private Long productoId;
        private int cantidad;
    }
}
