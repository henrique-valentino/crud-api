package com.example.desafiocrud.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.desafiocrud.controllers.ClientController;
import com.example.desafiocrud.entities.Client;

@Component
public class ClientModelAssembler implements RepresentationModelAssembler<Client, EntityModel<Client>> {

	@Override
	public EntityModel<Client> toModel(Client client) {
		return EntityModel.of(client, linkTo(methodOn(ClientController.class).findOne(client.getId())).withSelfRel(),
				linkTo(methodOn(ClientController.class).findAll()).withRel("employees"));
	}

}
