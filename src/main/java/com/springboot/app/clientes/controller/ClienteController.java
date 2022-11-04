package com.springboot.app.clientes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.app.clientes.entities.Cliente;
import com.springboot.app.clientes.repository.ClienteRepository;

@RestController
@RequestMapping("cliente/")
public class ClienteController 
{
	@Autowired
	ClienteRepository clienteRepository;
	
	/*@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Cliente> list(){
		return clienteRepository.findAll();
	}*/
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Cliente>> getAll(){
		List<Cliente> clientes = clienteRepository.findAll();
		ResponseEntity<List<Cliente>> responseEntity = new ResponseEntity<> (clientes, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping(value = "{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cliente get(@PathVariable Long id){
		Cliente cliente = clienteRepository.getReferenceById(id);
		return cliente;
	}
	
	@PostMapping
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
		clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<?> updateCliente(@PathVariable("id") Long id, @RequestBody Cliente nuevo){
		Cliente cliente = clienteRepository.getReferenceById(id);
		if(cliente == null) {
			return ResponseEntity.notFound().build();
		}
		nuevo.setId(id);
		cliente = clienteRepository.saveAndFlush(nuevo);
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Cliente> deleteCliente(@PathVariable("id") Long id){
		Cliente cliente = clienteRepository.getReferenceById(id);
		if(cliente == null) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.delete(cliente);
		return ResponseEntity.ok(cliente);
	}
}
