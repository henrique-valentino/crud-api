package com.example.desafiocrud.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.desafiocrud.entities.Client;
import com.example.desafiocrud.exceptions.RecordNotFoundException;
import com.example.desafiocrud.repositories.ClientRepository;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Client newClient(Client client) {
		return repository.save(client);
	}

	public Client update(Client newClient, Long id) {
		Client companyToUpdate = repository.findById(id).map(c -> {
			BeanUtils.copyProperties(newClient, c, "id");
			return repository.save(c);
		}).orElseThrow(() -> new RecordNotFoundException("Could not find client " + id));
		return companyToUpdate;
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Client findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Could not find client " + id));
	}

	public List<Client> findAll() {
		return repository.findAll();
	}
	
}
