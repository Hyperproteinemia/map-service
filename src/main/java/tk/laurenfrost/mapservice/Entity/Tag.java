package tk.laurenfrost.mapservice.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToMany(mappedBy = "tags")
    private Set<Article> articles = new HashSet<>();

}
