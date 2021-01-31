package com.example.desafiocrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.desafiocrud.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
