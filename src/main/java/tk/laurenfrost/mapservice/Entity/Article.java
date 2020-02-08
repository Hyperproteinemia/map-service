package tk.laurenfrost.mapservice.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(exclude = "tags")
public class Article {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String content;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "article_to_tag",
                joinColumns = {@JoinColumn(name = "article_id")},
                inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    @JsonBackReference
    private Set<Tag> tags = new HashSet<>();

    @NotEmpty
    @NotNull
    private String username;

    @NotNull
    @NotEmpty
    private String heading;

    @ElementCollection
    private Set<String> users_to_like  = new HashSet<>();

    @NotNull
    private Instant lastUpdate;

    private Instant expireDate;

    public void setHeading(String new_heading) {
        lastUpdate = Instant.now();
        heading = new_heading;
    }

    public void setContent(String new_content) {
        lastUpdate = Instant.now();
        content = new_content;
    }

    public void addTag(Tag tag) {
        lastUpdate = Instant.now();
        tags.add(tag);
    }
}
