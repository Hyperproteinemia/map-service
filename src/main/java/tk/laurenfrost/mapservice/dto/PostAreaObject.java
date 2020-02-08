package tk.laurenfrost.mapservice.dto;

import lombok.Data;
import tk.laurenfrost.mapservice.Entity.Area;
import tk.laurenfrost.mapservice.Entity.Article;
import tk.laurenfrost.mapservice.Entity.Tag;

import java.util.List;

@Data
public class PostAreaObject {
    Article article;
    Area area;
    List<Tag> tags;
}
