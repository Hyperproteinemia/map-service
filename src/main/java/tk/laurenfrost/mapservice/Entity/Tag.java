package tk.laurenfrost.mapservice.Entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

}
