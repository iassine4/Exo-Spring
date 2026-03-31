package fr.fms.springbasics;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import fr.fms.springbasics.entity.Article;
import fr.fms.springbasics.entity.Category;
import fr.fms.springbasics.repository.ArticleRepository;
import fr.fms.springbasics.repository.CategoryRepository;

@SpringBootApplication
public class SpringBasicsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBasicsApplication.class, args);
    }

    @Bean
    CommandLineRunner start(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        return args -> {
            initializeData(articleRepository, categoryRepository);

            displayAllArticles(articleRepository);
            displayArticlesByCategory(articleRepository);
            displayArticleById(articleRepository, 1L);
            displayArticleByDescription(articleRepository, "Running shoes");
            displayArticlesByDescriptionAndBrand(articleRepository, "dat", "Ni");
            displayArticlesByBrandAndMinPrice(articleRepository, "i", 50.0);
            displaySortedCategoryNames(categoryRepository);
            updateArticleById(articleRepository, 2L, "Nike", "Updated sneakers", 99.99);
            deleteArticleById(articleRepository, 3L);
        };
    }

    private void initializeData(ArticleRepository articleRepository, CategoryRepository categoryRepository) {
        // Initialise les données seulement si la base est vide
        if (categoryRepository.count() < 10 && articleRepository.count() < 10) {
            Category shoes = categoryRepository.save(new Category("Shoes"));
            Category clothes = categoryRepository.save(new Category("Clothes"));
            Category accessories = categoryRepository.save(new Category("Accessories"));
            
            Article article1 = new Article("Nike", "Running shoes", 89.99);
            article1.setCategory(shoes);

            Article article2 = new Article("Adidas", "Sneakers", 79.99);
            article2.setCategory(shoes);

            Article article3 = new Article("Puma", "Sport hoodie", 59.99);
            article3.setCategory(clothes);
            
            Article article4 = new Article("Reebok", "Classic leather sneakers", 74.99);
            article4.setCategory(shoes);

            Article article5 = new Article("New Balance", "Running shoes 530", 109.99);
            article5.setCategory(shoes);

            Article article6 = new Article("Converse", "Canvas high-top sneakers", 69.90);
            article6.setCategory(shoes);

            Article article7 = new Article("Under Armour", "Training shorts", 34.99);
            article7.setCategory(clothes);

            Article article8 = new Article("Levi's", "Slim fit jeans", 79.95);
            article8.setCategory(clothes);

            Article article9 = new Article("North Face", "Zip fleece jacket", 119.00);
            article9.setCategory(clothes);

            Article article10 = new Article("Ray-Ban", "Classic sunglasses", 149.99);
            article10.setCategory(accessories);

            Article article11 = new Article("Eastpak", "Urban backpack", 54.99);
            article11.setCategory(accessories);

            Article article12 = new Article("Casio", "Digital sport watch", 39.90);
            article12.setCategory(accessories);

            Article article13 = new Article("Puma", "Baseball cap", 24.99);
            article13.setCategory(accessories);

            articleRepository.save(article1);
            articleRepository.save(article2);
            articleRepository.save(article3);
            articleRepository.saveAll(List.of(
            	    article4,
            	    article5,
            	    article6,
            	    article7,
            	    article8,
            	    article9,
            	    article10,
            	    article11,
            	    article12,
            	    article13
            	));
        }
    }

    private void displayAllArticles(ArticleRepository articleRepository) {
        // Affiche tous les articles
        System.out.println("----- All articles -----");
        articleRepository.findAll().forEach(article -> {
            System.out.println(article);
        });
    }

    private void displayArticlesByCategory(ArticleRepository articleRepository) {
        // Affiche tous les articles de la catégorie Shoes
        System.out.println("----- Articles of category Shoes -----");
        articleRepository.findByCategoryName("Shoes").forEach(article -> {
            System.out.println(article);
        });
    }

    private void displayArticleById(ArticleRepository articleRepository, Long articleId) {
        // Recherche un article par son identifiant
        System.out.println("----- Find article by id -----");
        articleRepository.findById(articleId).ifPresentOrElse(article -> {
            System.out.println(article);
        }, () -> {
            System.out.println("Article with id " + articleId + " not found.");
        });
    }

    private void displayArticleByDescription(ArticleRepository articleRepository, String description) {
        // Recherche un article par description exacte
        System.out.println("----- Find article by description -----");
        Article article = articleRepository.findByDescription(description);
        if (article != null) {
            System.out.println(article);
        } else {
            System.out.println("No article found with description: " + description);
        }
    }

    private void displayArticlesByDescriptionAndBrand(ArticleRepository articleRepository, String descriptionKeyword, String brandKeyword) {
        // Recherche les articles dont la description contient un mot
        // et dont la marque contient un mot
        System.out.println("----- Articles by description and brand -----");
        articleRepository.findByDescriptionContainingAndBrandContaining(descriptionKeyword, brandKeyword)
                .forEach(article -> {
                    System.out.println(article);
                });
    }

    private void displayArticlesByBrandAndMinPrice(ArticleRepository articleRepository, String brandKeyword, Double minPrice) {
        // Recherche les articles dont la marque contient un mot-clé
        // et dont le prix est supérieur à une valeur
        System.out.println("----- Articles by brand keyword and minimum price -----");
        articleRepository.findByBrandContainingAndPriceGreaterThan(brandKeyword, minPrice)
                .forEach(article -> {
                    System.out.println(article);
                });
    }

    private void displaySortedCategoryNames(CategoryRepository categoryRepository) {
        // Affiche les noms des catégories par ordre croissant puis décroissant
        System.out.println("----- Categories sorted by name ascending -----");
        categoryRepository.findAll(Sort.by("name").ascending()).forEach(category -> {
            System.out.println(category.getName());
        });

        System.out.println("----- Categories sorted by name descending -----");
        categoryRepository.findAll(Sort.by("name").descending()).forEach(category -> {
            System.out.println(category.getName());
        });
    }

    private void updateArticleById(ArticleRepository articleRepository, Long articleId, String newBrand, String newDescription, Double newPrice) {
        // Met à jour un article à partir de son id
        System.out.println("----- Update article by id -----");
        articleRepository.findById(articleId).ifPresentOrElse(article -> {
            System.out.println("Before update: " + article);

            article.setBrand(newBrand);
            article.setDescription(newDescription);
            article.setPrice(newPrice);

            Article updatedArticle = articleRepository.save(article);
            System.out.println("After update: " + updatedArticle);
        }, () -> {
            System.out.println("Article with id " + articleId + " not found.");
        });
    }

    private void deleteArticleById(ArticleRepository articleRepository, Long articleId) {
        // Supprime un article à partir de son id
        System.out.println("----- Delete article by id -----");
        articleRepository.findById(articleId).ifPresentOrElse(article -> {
            System.out.println("Article found before delete: " + article);
            articleRepository.deleteById(articleId);
            System.out.println("Article with id " + articleId + " deleted.");
        }, () -> {
            System.out.println("Article with id " + articleId + " not found.");
        });
    }
}