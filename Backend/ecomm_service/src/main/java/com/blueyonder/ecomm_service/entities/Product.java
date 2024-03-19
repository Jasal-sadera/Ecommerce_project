package com.blueyonder.ecomm_service.entities;

import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Product")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Product_id")
	private Integer productId;
	@Column(name="ProductName")
	private String productName;
	@Column(name="Price")
	private Integer price;
	@Column(name="Description")
	private String des;
	@ManyToOne
	@JoinColumn(name="catId")
	@JsonIgnoreProperties("products")
	//@JsonBackReference
	private Category category;
	public void setCategoryId(Integer catId) {
	    if (this.category == null) {
	        this.category = new Category();
	    }
	    this.category.setCategoryId(catId);
	}

	
}
