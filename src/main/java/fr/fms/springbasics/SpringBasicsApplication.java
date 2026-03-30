package fr.fms.springbasics;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.fms.springbasics.entity.Article;
import fr.fms.springbasics.repository.ArticleRepository;

@SpringBootApplication
public class SpringBasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBasicsApplication.class, args);
	}

	@Bean
    CommandLineRunner start(ArticleRepository articleRepository) {
        return args -> {
            // Pour éviter d'insérer les mêmes données à chaque démarrage
            if (articleRepository.count() == 0) {
                articleRepository.save(new Article("Nike", "Running shoes", 89.99));
                articleRepository.save(new Article("Adidas", "Sport hoodie", 59.90));
                articleRepository.save(new Article("Nike", "Training t-shirt", 29.50));
            }

            System.out.println("----- All articles -----");

            articleRepository.findAll().forEach(article -> {
                System.out.println(article);
            });

            System.out.println("----- Nike articles -----");

            articleRepository.findByBrand("Nike").forEach(article -> {
                System.out.println(article);
            });
        };
    }
}
