package tk.laurenfrost.mapservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.laurenfrost.mapservice.Entity.Area;
import tk.laurenfrost.mapservice.Entity.Article;
import tk.laurenfrost.mapservice.Entity.Tag;
import tk.laurenfrost.mapservice.Exceptions.UserHaveNoPermissionToModifyFile;
import tk.laurenfrost.mapservice.Service.AreaService;
import tk.laurenfrost.mapservice.Service.ArticleService;
import tk.laurenfrost.mapservice.Service.TagService;
import tk.laurenfrost.mapservice.dto.AreaArticleId;
import tk.laurenfrost.mapservice.dto.PostAreaObject;

import java.time.Instant;
import java.util.ArrayList;
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
    ResponseEntity<List<AreaArticleId>> getAllAreas() {
        List<Area> areas = areaService.getAll();
        ArrayList<AreaArticleId> areaArticleIds = new ArrayList<>(areas.size());

        int i = 0;
        for (Area area : areas) {
            AreaArticleId areaArticleId = new AreaArticleId();
            areaArticleId.setArticleId(area.getArticle().getId());
            areaArticleId.setArea(area);
            areaArticleIds.add(areaArticleId);
        }

        return new ResponseEntity<>(areaArticleIds, HttpStatus.OK);
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

        article.setLastUpdate(Instant.now());

        article = articleService.addArticle(article);

        area.setArticle(article);

        area.setUsername(username);

        area = areaService.addArea(area);
        httpStatus = HttpStatus.OK;

        return new ResponseEntity<>(area, httpStatus);

    }

    @DeleteMapping("map/area/{id}")
    ResponseEntity<String> deleteArea(@RequestHeader(name = "username") String username, @PathVariable Long id) {
        Area area = areaService.getById(id);

        if (area.getUsername().equals(username)) {
            throw new UserHaveNoPermissionToModifyFile();
        }

        areaService.removeById(id);
        return new ResponseEntity<>("Complete", HttpStatus.OK);
    }
}
