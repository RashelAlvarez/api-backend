package com.apibackend.app.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apibackend.app.entity.Cliente;
import com.apibackend.app.services.IClienteService;

import jakarta.validation.Valid;

@CrossOrigin(origins = { "http://localhost:4200" }) // sirve para conectar esta aplicacion con Angular
@RestController
public class ClienteController {

	@Autowired
	private IClienteService clienteService;

	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll();
	}

	/*@GetMapping("/clientes/page/{page}")
	public Page<Cliente> index(@PathVariable Integer page) {
		return clienteService.findAll(PageRequest.of(page, 4));
	}*/
	
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap<>();

		try {
			cliente = clienteService.findById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la bd");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (cliente == null) {
			response.put("mensaje", "El cliente id: ".concat(id.toString().concat("no existe en  la bd")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	@PostMapping("/clientes")
	public ResponseEntity<?> save(@Valid @RequestBody Cliente cliente, BindingResult result) {

		Cliente clienteNew = null;
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
		
			  List<String> errors= new ArrayList<>(); for(FieldError err
			  :result.getFieldErrors()) { errors.add("El campo " + err.getField() + " " +
			  err.getDefaultMessage()); } response.put("errors", errors); return new
			  ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			 
		/*	List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "El campo " +  err.getField();
			}).collect(Collectors.toList());*/
		}
		try {
			clienteNew = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la bd");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El cliente ha sido creado con exito");
		response.put("cliente", clienteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, @PathVariable Long id, BindingResult result) {
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteUpdate = null;
		Map<String, Object> response = new HashMap<>();
		
		if (result.hasErrors()) {
			
			  List<String> errors= new ArrayList<>(); for(FieldError err
			  :result.getFieldErrors()) { errors.add("El campo " + err.getField() + " " +
			  err.getDefaultMessage()); } response.put("errors", errors); return new
			  ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			 
		/*	List<String> errors = result.getFieldErrors().stream().map(err -> {
				return "El campo " +  err.getField();
			}).collect(Collectors.toList());*/
		}
		
		if (clienteActual == null) {
			response.put("mensaje",
					"No se puedo editar ,El cliente id: ".concat(id.toString().concat("no existe en  la bd")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setEmail(cliente.getEmail());

			clienteUpdate = clienteService.save(clienteActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la bd");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El cliente ha sido actualizado con exito");
		response.put("cliente", clienteUpdate);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {

		Map<String, Object> response = new HashMap<>();
		try {
			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar el registro en la bd");
			response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El cliente ha sido eliminado con exito");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
}
