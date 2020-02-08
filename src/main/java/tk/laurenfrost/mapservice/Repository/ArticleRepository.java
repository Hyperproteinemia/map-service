package tk.laurenfrost.mapservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tk.laurenfrost.mapservice.Entity.Article;
import tk.laurenfrost.mapservice.Entity.Tag;

import java.util.List;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByUsername(String username);
/*
    @Query("select distinct article from Article " +
            "article join article.tags tag where tag.name = :tag_name")*/
    List<Article> findAllByTags_Id(Long tag_id);
}
