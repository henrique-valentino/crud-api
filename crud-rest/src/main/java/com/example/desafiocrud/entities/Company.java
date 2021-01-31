package com.example.desafiocrud.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity(name = "tb_company")
@SequenceGenerator(name = "COMPANY_SEQ", sequenceName = "COMPANY_SEQ", initialValue = 1, allocationSize = 1)
public class Company implements Serializable {
	
	private static final long serialVersionUID = -8512820777248626184L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "COMPANY_SEQ")
	private Long id;
	
	@NotNull(message = "Name is mandatory")
	@Size(min=2, max=30)
	private String name;
	
	@NotNull(message = "Address is mandatory")
	@Size(min=2, max=30)
	private String address;
	
	public Company(String name, String address) {
		this.name = name;
		this.address = address;
	}

}
