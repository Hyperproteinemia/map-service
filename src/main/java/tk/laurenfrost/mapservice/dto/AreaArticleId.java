package tk.laurenfrost.mapservice.dto;

import lombok.Data;
import tk.laurenfrost.mapservice.Entity.Area;

@Data
public class AreaArticleId {
    Area area;
    Long articleId;

    public void setArea(Area a) {
        area = a;
    }

    public void setArticleId(Long id) {
        articleId = id;
    }
}
