package com.example.desafiocrud.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.example.desafiocrud.controllers.ClientController;
import com.example.desafiocrud.controllers.CompanyController;
import com.example.desafiocrud.entities.Company;

@Component
public class CompanyModelAssembler implements RepresentationModelAssembler<Company, EntityModel<Company>> {

	@Override
	public EntityModel<Company> toModel(Company company) {
		return EntityModel.of(company, linkTo(methodOn(ClientController.class).findOne(company.getId())).withSelfRel(),
				linkTo(methodOn(CompanyController.class).findAll()).withRel("employees"));
	}

}
