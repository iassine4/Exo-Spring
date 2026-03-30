package fr.fms.springbasics.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Article> articles = new ArrayList<>();
	
	public Category() {
        // Constructeur vide obligatoire pour JPA
    }
	
	public Category(String name) {
		 // Constructeur pratique pour créer une catégorie
        this.name = name;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticles() {
        // Retourne la liste des articles de la catégorie
        return articles;
    }

    public void setArticles(List<Article> articles) {
        // Modifie la liste des articles de la catégorie
        this.articles = articles;
    }

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
}
