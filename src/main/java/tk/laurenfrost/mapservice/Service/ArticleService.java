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

    public List<Article> getAllById(Long id) {
        return articleRepository.findAllById(id);
    }

    public List<Article> getAllByUsername(String username) {
        return articleRepository.findAllByUsername(username);
    }

    public List<Article> getAllByTag(Long tag_id) {
        return articleRepository.findAllByTags_Id(tag_id);
    }

    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }
}
