package tk.laurenfrost.mapservice.Service;

import org.springframework.stereotype.Service;
import tk.laurenfrost.mapservice.Entity.Article;
import tk.laurenfrost.mapservice.Exceptions.ArticleNotFoundException;
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

    public Article getById(Long id) {
        return articleRepository.findById(id).orElseThrow(ArticleNotFoundException::new);
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

    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }
}
