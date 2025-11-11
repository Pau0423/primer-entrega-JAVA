package Facturacion_Primera.Entrega.primer_entrega.controllers;

import Facturacion_Primera.Entrega.primer_entrega.model.Cliente;
import Facturacion_Primera.Entrega.primer_entrega.responses.ErrorResponse;
import Facturacion_Primera.Entrega.primer_entrega.services.ClientesService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClientesService clienteService;

    public ClienteController(ClientesService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        try {
            List<Cliente> clientes = clienteService.listar();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long clienteId) {
        try {
            Cliente cliente = clienteService.obtener(clienteId);
            return ResponseEntity.ok(cliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCliente(@RequestBody Cliente cliente) {
        try {
            Cliente clienteCreado = clienteService.crear(cliente);
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
        } catch (IllegalStateException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse("Conflicto", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> updateClienteById(@PathVariable Long clienteId,
                                                     @RequestBody Cliente clienteActualizado) {
        try {
            Cliente cliente = clienteService.actualizar(clienteId, clienteActualizado);
            return ResponseEntity.ok(cliente);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long clienteId) {
        try {
            clienteService.eliminar(clienteId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
