package fr.fms.springbasics.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import fr.fms.springbasics.entity.Article;

/**
 * Repository JPA pour l'entité Article.
 * Spring créera automatiquement l'implémentation au démarrage.
 */
public interface ArticleRepository extends JpaRepository<Article, Long> {

    /**
     * Recherche tous les articles d'une marque donnée.
     * Spring Data JPA génère automatiquement la requête
     * à partir du nom de la méthode.
     */
    List<Article> findByBrand(String brand);
    List<Article> findByCategoryName(String name);
    Article findByDescription(String description);
}
