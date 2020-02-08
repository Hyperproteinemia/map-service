package tk.laurenfrost.mapservice.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Area {

    @Id
    @GeneratedValue
    private Long id;

    private float x;
    private float y;

    private float radius;

    private String username;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Article article;
}
