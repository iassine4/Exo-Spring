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
            
        //-----------		Exercice 1.2		--------
            
            System.out.println("----- Find article by id -----");
            articleRepository.findById(5L).ifPresent(article -> {
                System.out.println(article);
            });
            
            System.out.println("----- Find article by description -----");
            Article articleByDescription = articleRepository.findByDescription("Running shoes");
            if (articleByDescription != null) {
                System.out.println(articleByDescription);
            }
            
            System.out.println("----- All articles -----");
            articleRepository.findAll().forEach(article -> {
                System.out.println(article);
            });
            
        //-----------		Exercice 1.3		--------
            
            System.out.println("----- Articles with description containing 'shoe' and brand containing 'Ni' -----");
            articleRepository.findByDescriptionContainingAndBrandContaining("shoe", "Ni")
                    .forEach(article -> {
                        System.out.println(article);
            });
            
            System.out.println("----- Articles with description and brand -----");
            articleRepository.findByDescriptionAndBrand("Running shoes", "Nike")
                    .forEach(article -> {
                        System.out.println(article);
            });
            
        //------------		Exercice 1.4		---------
            
            System.out.println("----- Delete article by id -----");

            Long articleIdToDelete = 4L;

            articleRepository.findById(articleIdToDelete).ifPresentOrElse(article -> {
                System.out.println("Article found before delete: " + article);

                articleRepository.deleteById(articleIdToDelete);

                System.out.println("Article with id " + articleIdToDelete + " deleted.");
            }, () -> {
                System.out.println("Article with id " + articleIdToDelete + " not found.");
            });

            System.out.println("----- All articles after delete -----");
            articleRepository.findAll().forEach(article -> {
                System.out.println(article);
            });
        };
    }
}