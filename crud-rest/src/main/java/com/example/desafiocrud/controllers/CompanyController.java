package com.example.desafiocrud.controllers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

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

import com.example.desafiocrud.assemblers.CompanyModelAssembler;
import com.example.desafiocrud.entities.Company;
import com.example.desafiocrud.services.CompanyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@Api(value = "API REST Company")
@RestController
@RequestMapping(value = "/companies")
public class CompanyController {

	@Autowired
	private CompanyService service;

	@Autowired
	private CompanyModelAssembler assembler;

	@ApiOperation(value="Adiciona uma nova empresa.")
	@PostMapping
	public ResponseEntity<?> newCompany(@RequestBody Company newCompany) {
		try {
			Company savedCompany = service.newCompany(newCompany);
			EntityModel<Company> CompanyResource = EntityModel.of(savedCompany,
					linkTo(methodOn(CompanyController.class).findOne(savedCompany.getId())).withSelfRel());
			return ResponseEntity.created(new URI(CompanyResource.getRequiredLink(IanaLinkRelations.SELF).getHref()))
					.body(CompanyResource);
		} catch (URISyntaxException e) {
			return ResponseEntity.badRequest().body("Unable to create " + newCompany);
		}
	}

	@ApiOperation(value="Busca uma empresa pelo o Id.")
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<Company>> findOne(@PathVariable Long id) {
		Company Company = service.findById(id);
		return ResponseEntity.ok(assembler.toModel(Company));
	}

	@ApiOperation(value="Retorna uma lista de empresas cadastrados.")
	@GetMapping
	public ResponseEntity<CollectionModel<EntityModel<Company>>> findAll() {
		List<EntityModel<Company>> Companys = service.findAll().stream().map(assembler::toModel)
				.collect(Collectors.toList());

		return ResponseEntity
				.ok(CollectionModel.of(Companys, linkTo(methodOn(CompanyController.class).findAll()).withSelfRel()));
	}

	@ApiOperation(value="Altera uma empresa.")
	@PutMapping("/{id}")
	public ResponseEntity<?> replaceCompany(@RequestBody Company newCompanye, @PathVariable Long id) {
		EntityModel<Company> entityModel = assembler.toModel(service.update(newCompanye, id));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@ApiOperation(value="Deleta uma empresa.")
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
