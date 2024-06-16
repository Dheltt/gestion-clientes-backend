package com.gestion_clientes.gestion_clientes_backend.controllers;

import com.gestion_clientes.gestion_clientes_backend.exception.ResourceNotFoundException;
import com.gestion_clientes.gestion_clientes_backend.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.gestion_clientes.gestion_clientes_backend.repository.ClienteRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")//esto es para que react acceda al backend/servidor
@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }
    @PostMapping("/clientes")
    public Cliente saveClient(@RequestBody Cliente cliente){
        return clienteRepository.save(cliente);
    }
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> ListarClienteById(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("el cliente con el id: "+id+" no existe"));
        return ResponseEntity.ok(cliente);
    }
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id,@RequestBody Cliente  clienteRequest){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("el cliente con el id: "+id+" no existe"));
        cliente.setNombre(clienteRequest.getNombre());
        cliente.setApellido(clienteRequest.getApellido());
        cliente.setEmail(clienteRequest.getEmail());
        Cliente clienteActualizado = clienteRepository.save(cliente);
        return ResponseEntity.ok(clienteActualizado);
    }
    @DeleteMapping("clientes/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteClient(@PathVariable Long id){
        Cliente cliente = clienteRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("el cliente con el id: "+id+" no existe"));
        clienteRepository.delete(cliente);
        Map<String,Boolean> response = new HashMap<>();
        response.put("delete",true);
        return ResponseEntity.ok(response);
    }
}
