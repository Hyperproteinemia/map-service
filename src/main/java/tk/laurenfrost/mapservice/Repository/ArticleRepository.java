package tk.laurenfrost.mapservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.laurenfrost.mapservice.Entity.Area;

public interface ArticleRepository extends JpaRepository<Area, Long> {
}
