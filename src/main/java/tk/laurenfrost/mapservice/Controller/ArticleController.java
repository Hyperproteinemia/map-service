package tk.laurenfrost.mapservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.mapservice.Entity.Article;
import tk.laurenfrost.mapservice.Service.ArticleService;

import java.util.List;

@RestController
public class ArticleController {

    final
    ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
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
}
