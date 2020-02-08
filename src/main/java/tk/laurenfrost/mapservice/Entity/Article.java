package tk.laurenfrost.mapservice.Entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Article {

    @Id
    @NotNull
    @NotEmpty
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String content;

//    @ManyToMany(mappedBy = "id")
//    Set<Tag> tags;

}
