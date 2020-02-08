package tk.laurenfrost.mapservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.laurenfrost.mapservice.Entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
