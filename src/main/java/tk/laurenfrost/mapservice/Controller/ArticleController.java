package tk.laurenfrost.mapservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.mapservice.Entity.Article;
import tk.laurenfrost.mapservice.Entity.Tag;
import tk.laurenfrost.mapservice.Exceptions.UserHaveNoPermissionToModifyFile;
import tk.laurenfrost.mapservice.Service.ArticleService;
import tk.laurenfrost.mapservice.Service.TagService;
import tk.laurenfrost.mapservice.dto.UpdateArticle;

import java.util.List;

@RestController
public class ArticleController {

    final TagService tagService;
    final ArticleService articleService;

    public ArticleController(ArticleService articleService, TagService tagService) {
        this.articleService = articleService;
        this.tagService = tagService;
    }

    @GetMapping("/map/article/{id}")
    ResponseEntity<String> getArticlesById(@PathVariable Long id) {
        HttpStatus httpStatus;
        String jsonResponse;

        Article articles = articleService.getById(id);

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonResponse = mapper.writeValueAsString(articles);
            httpStatus = HttpStatus.OK;
        } catch (JsonProcessingException e) {
            jsonResponse = "getArticlesById failed, die please";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(jsonResponse, httpStatus);
    }

    @GetMapping("/map/article")
    ResponseEntity<String> getAllArticles() {
        HttpStatus httpStatus;
        String jsonResponse;

        List<Article> articles = articleService.getAll();

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonResponse = mapper.writeValueAsString(articles);
            httpStatus = HttpStatus.OK;
        } catch (JsonProcessingException e) {
            jsonResponse = "Failed";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(jsonResponse, httpStatus);
    }

    @PatchMapping("/map/article/{id}")
    ResponseEntity<String> patchArticle(@RequestHeader(name = "username") String username,
            @RequestBody UpdateArticle updateArticle, @PathVariable Long id) {
        Article article = articleService.getById(id);
        if (!article.getUsername().equals(username)) {
            throw new UserHaveNoPermissionToModifyFile();
        }
        if (updateArticle.getNew_heading() != null) {
            article.setHeading(updateArticle.getNew_heading());
        }
        if (updateArticle.getNew_description() != null) {
            article.setContent(updateArticle.getNew_description());
        }
        if (updateArticle.getNew_tags() != null) {
            article.getTags().clear();
            articleService.updateArticle(article);
            for (String i : updateArticle.getNew_tags()) {
                Tag tag = tagService.findByName(i);
                article.addTag(tag);
            }
        }

        articleService.updateArticle(article);

        return new ResponseEntity<>("Complete", HttpStatus.OK);
    }

    @PostMapping("/map/article/{id}/like")
    ResponseEntity<String> addLike(@RequestHeader(name = "username") String username,
                                   @PathVariable Long id) {
        Article article = articleService.getById(id);
        article.getUsers_to_like().add(username);
        articleService.updateArticle(article);
        return new ResponseEntity<>("Complete", HttpStatus.OK);
    }

    @DeleteMapping("/map/article/{id}/like")
    ResponseEntity<String> removeLike(@RequestHeader(name = "username") String username,
                                      @PathVariable Long id) {
        Article article = articleService.getById(id);
        if (!article.getUsers_to_like().contains(username)) {
            throw new UserHaveNoPermissionToModifyFile();
        }
        article.getUsers_to_like().remove(username);
        articleService.updateArticle(article);
        return new ResponseEntity<>("Complete", HttpStatus.OK);
    }
}
