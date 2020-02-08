package tk.laurenfrost.mapservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.laurenfrost.mapservice.Entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
