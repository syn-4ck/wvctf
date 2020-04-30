package com.julianfm.wvctf.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(value = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Column(length=50, nullable=false)
	@NotBlank(message = "Name is mandatory")
	private String name;
	
	private String description;
	
	@ManyToOne(optional = false)
	private Category category;
	
	@Column(length=5, nullable=false)
	@NotBlank(message = "Size is mandatory")
	private String size;
	
	private String color;
	
	@NotNull(message = "Price is mandatory")
	private double price;

	@ManyToOne(optional = false)
	private Users vendor;

}
