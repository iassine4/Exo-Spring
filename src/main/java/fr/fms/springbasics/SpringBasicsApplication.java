package fr.fms.springbasics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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

            // =========================
            // Initialisation des catégories
            // =========================
            if (categoryRepository.count() == 0) {
                categoryRepository.save(new Category("Shoes"));
                categoryRepository.save(new Category("Clothes"));
                categoryRepository.save(new Category("Accessories"));
            }

            // =========================
            // Initialisation des articles
            // =========================
            if (articleRepository.count() == 0) {
                articleRepository.save(new Article("Nike", "Running shoes", 89.99));
                articleRepository.save(new Article("Adidas", "Sport hoodie", 59.90));
                articleRepository.save(new Article("Puma", "Cap", 19.99));
            }

            // =========================
            // Affichage des catégories
            // =========================
            System.out.println("----- All categories -----");
            categoryRepository.findAll().forEach(category -> {
                System.out.println(category);
            });

            // =========================
            // Affichage des articles
            // =========================
            System.out.println("----- All articles -----");
            articleRepository.findAll().forEach(article -> {
                System.out.println(article);
            });

            // =========================
            // Recherche des articles Nike
            // =========================
            System.out.println("----- Nike articles -----");
            articleRepository.findByBrand("Nike").forEach(article -> {
                System.out.println(article);
            });
        };
    }
}