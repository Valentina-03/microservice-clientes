package com.springboot.app.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.app.clientes.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
