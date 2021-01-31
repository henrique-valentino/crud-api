package com.example.desafiocrud.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafiocrud.entities.Company;
import com.example.desafiocrud.exceptions.RecordNotFoundException;
import com.example.desafiocrud.repositories.CompanyRepository;

@Service
public class CompanyService {

	@Autowired
	private CompanyRepository repository;

	public Company newCompany(Company comapny) {
		return repository.save(comapny);
	}

	public Company update(Company newCompany, Long id) {
		Company companyToUpdate = repository.findById(id).map(c -> {
			BeanUtils.copyProperties(newCompany, c, "id");
			return repository.save(c);
		}).orElseThrow(() -> new RecordNotFoundException("Could not find comapny " + id));
		return companyToUpdate;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Company findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Could not find comapny " + id));
	}

	public List<Company> findAll() {
		return repository.findAll();
	}

}
