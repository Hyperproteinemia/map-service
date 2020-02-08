package tk.laurenfrost.mapservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.mapservice.Entity.Area;
import tk.laurenfrost.mapservice.Entity.Article;
import tk.laurenfrost.mapservice.Entity.Tag;
import tk.laurenfrost.mapservice.Service.AreaService;
import tk.laurenfrost.mapservice.Service.ArticleService;
import tk.laurenfrost.mapservice.Service.TagService;
import tk.laurenfrost.mapservice.dto.PostAreaObject;

import java.time.Instant;
import java.util.List;

@RestController
public class AreaController {

    private final AreaService areaService;
    private final TagService tagService;
    private ArticleService articleService;

    public AreaController(AreaService areaService, TagService tagService, ArticleService articleService) {
        this.areaService = areaService;
        this.tagService = tagService;
        this.articleService = articleService;
    }

    @GetMapping("/map/area")
    ResponseEntity<String> getAllAreas() {
        HttpStatus httpStatus;
        String jsonResponse;

        List<Area> areas = areaService.getAll();

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonResponse = mapper.writeValueAsString(areas);
            httpStatus = HttpStatus.OK;
        } catch (JsonProcessingException e) {
            jsonResponse = "Failed";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(jsonResponse, httpStatus);
    }

    @PostMapping("/map/area")
    ResponseEntity<Area> postArea(@RequestHeader(name = "username") String username,
                                    @RequestBody PostAreaObject postAreaObject) {
        HttpStatus httpStatus;
        Area area = postAreaObject.getArea();
        Article article = postAreaObject.getArticle();
        List<Tag> tags = postAreaObject.getTags();

        for (Tag tag: tags) {
            // TODO: optimize
            tag = tagService.findByName(tag.getName());
            article.getTags().add(tag);
        }


        article.setUsername(username);

        article.setCreatedWhen(Instant.now());

        article = articleService.addArticle(article);

        area.setArticle(article);

        area.setUsername(username);

        area = areaService.addArea(area);
        httpStatus = HttpStatus.OK;

        return new ResponseEntity<>(area, httpStatus);

    }
}
