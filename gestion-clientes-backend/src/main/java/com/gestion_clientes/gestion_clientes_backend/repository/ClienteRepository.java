package com.gestion_clientes.gestion_clientes_backend.repository;

import com.gestion_clientes.gestion_clientes_backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
