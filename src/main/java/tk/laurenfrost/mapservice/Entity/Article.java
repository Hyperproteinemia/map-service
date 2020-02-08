package tk.laurenfrost.mapservice.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
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
    private List<String> users_to_like  = new ArrayList<>();

    @NotNull
    private Instant createdWhen;

    private Instant modifiedWhen;

    private Instant expireDate;

    public void setHeading(String new_heading) {

    }
}
