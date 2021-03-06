package tk.laurenfrost.mapservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tk.laurenfrost.mapservice.Entity.Tag;
import tk.laurenfrost.mapservice.Service.TagService;

import java.util.List;

@RestController
public class TagController {

    final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/map/tag")
    ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.getAll();

        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    @PostMapping("/map/tag")
    ResponseEntity<String> postTag(@RequestBody Tag tag) {

        tag = tagService.addTag(tag);

        HttpStatus httpStatus;
        String jsonResponse;

        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonResponse = mapper.writeValueAsString(tag);
            httpStatus = HttpStatus.OK;
        } catch (JsonProcessingException e) {
            jsonResponse = "Failed";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(jsonResponse, httpStatus);
    }
}
