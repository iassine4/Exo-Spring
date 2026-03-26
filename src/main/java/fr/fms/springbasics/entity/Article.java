package fr.fms.springbasics.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String description;
    private Double price;
    
    public Article() {
    	// Constructeur vide obligatoire pour JPA
    }
    
    public Article(Long id, String brand, String description, Double price) {
    	
    	// id générer par la bdd
    	
    	this.brand = brand;
    	this.description = description;
    	this.price = price;
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
    
	@Override
	public String toString() {
		return "Article [id=" + id + ", brand=" + brand + ", description=" + description + ", price=" + price + "]";
	}
}
