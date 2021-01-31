package com.example.desafiocrud.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.desafiocrud.assemblers.ClientModelAssembler;
import com.example.desafiocrud.entities.Client;
import com.example.desafiocrud.services.ClientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value = "API REST Client")
@RestController
@RequestMapping(value = "/clients")
public class ClientController {

	@Autowired
	private ClientService service;

	@Autowired
	private ClientModelAssembler assembler;

	@ApiOperation(value="Adiciona um novo cliente.")
	@PostMapping
	public ResponseEntity<?> newClient(@RequestBody @Valid Client newClient) {
		try {
			Client savedClient = service.newClient(newClient);
			EntityModel<Client> clientResource = EntityModel.of(savedClient,
					linkTo(methodOn(ClientController.class).findOne(savedClient.getId())).withSelfRel());
			return ResponseEntity.created(new URI(clientResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
					.body(clientResource);
		} catch (URISyntaxException e) {
			return ResponseEntity.badRequest().body("Unable to create " + newClient);
		}
	}

	
	@ApiOperation(value="Busca um cliente pelo o Id.")
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<Client>> findOne(@PathVariable Long id) {
		Client client = service.findById(id);
		return ResponseEntity.ok(assembler.toModel(client));
	}

	@ApiOperation(value="Retorna uma lista de clientes cadastrados.")
	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<Client>>> findAll() {
		List<EntityModel<Client>> clients = service.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return ResponseEntity
				.ok(CollectionModel.of(clients, linkTo(methodOn(ClientController.class).findAll()).withSelfRel()));
	}

	@ApiOperation(value="Altera um cliente.")
	@PutMapping("/{id}")
	public ResponseEntity<?> replaceClient(@RequestBody Client newCliente, @PathVariable Long id) {
		EntityModel<Client> entityModel = assembler.toModel(service.update(newCliente, id));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@ApiOperation(value="Deleta um cliente por Id.")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
		try {
			service.delete(id);
			return ResponseEntity.noContent().build();
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
