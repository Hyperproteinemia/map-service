package tk.laurenfrost.mapservice.Service;

import org.springframework.stereotype.Service;
import tk.laurenfrost.mapservice.Entity.Article;
import tk.laurenfrost.mapservice.Repository.ArticleRepository;

import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAll() {
        return articleRepository.findAll();
    }
}
