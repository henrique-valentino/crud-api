package com.example.desafiocrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.desafiocrud.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

}
