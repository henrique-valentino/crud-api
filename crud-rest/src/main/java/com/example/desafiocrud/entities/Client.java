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
@Entity(name = "tb_client")
@SequenceGenerator(name = "CLIENT_SEQ", sequenceName = "CLIENT_SEQ", initialValue = 1, allocationSize = 1)
public class Client implements Serializable{
	private static final long serialVersionUID = 6174475267172793126L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CLIENT_SEQ")
	private Long id;
	
	@NotNull(message = "Name is mandatory")
	@Size(min=2, max=30)
	private String name;
	
	@NotNull(message = "Role is mandatory")
	@Size(min=2, max=30)
	private String role;
	
	public Client(String name, String role) {
		this.name = name;
		this.role = role;
	}
	
}
