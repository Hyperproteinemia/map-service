package tk.laurenfrost.mapservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tk.laurenfrost.mapservice.Entity.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {
}
