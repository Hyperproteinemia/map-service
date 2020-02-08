package tk.laurenfrost.mapservice.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.HashSet;
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

    @NotNull
    private Instant createdWhen;

    private Instant modifiedWhen;

    private Instant expireDate;
}
