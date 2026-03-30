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

            // On évite de réinsérer les mêmes données à chaque démarrage
            if (categoryRepository.count() == 0 && articleRepository.count() == 0) {

                Category shoes = categoryRepository.save(new Category("Shoes"));
                Category clothes = categoryRepository.save(new Category("Clothes"));

                Article article1 = new Article("Nike", "Running shoes", 89.99);
                article1.setCategory(shoes);

                Article article2 = new Article("Adidas", "Sneakers", 79.99);
                article2.setCategory(shoes);

                Article article3 = new Article("Puma", "Sport hoodie", 59.99);
                article3.setCategory(clothes);

                articleRepository.save(article1);
                articleRepository.save(article2);
                articleRepository.save(article3);
            }

            System.out.println("----- Articles de la catégorie Shoes (méthode 1) -----");

            Category shoesCategory = categoryRepository.findByName("Shoes");
            if (shoesCategory != null) {
                shoesCategory.getArticles().forEach(article -> {
                    System.out.println(article);
                });
            }

            System.out.println("----- Articles de la catégorie Shoes (méthode 2) -----");

            articleRepository.findByCategoryName("Shoes").forEach(article -> {
                System.out.println(article);
            });
        };
    }
}