package com.blueyonder.ecomm_service.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Category")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Category_id")
	private Integer categoryId;
	@Column(name="Category_name")
	private String categoryName;
	@Column(name="Description")
	private String des;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="category")
	//@JsonManagedReference
	@JsonIgnoreProperties("category")
	private List<Product> products;

}
