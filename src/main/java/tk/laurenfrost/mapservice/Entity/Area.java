package tk.laurenfrost.mapservice.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@Entity
public class Area {

    @Id
    @GeneratedValue
    private Long id;

    private float x;
    private float y;

    private String username;

    @OneToOne
    private Article article;
}
