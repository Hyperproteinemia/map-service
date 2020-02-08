package tk.laurenfrost.mapservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateArticle {
    List<String> new_tags;
    Long article_id;
    String new_heading;
    String new_description;
}
