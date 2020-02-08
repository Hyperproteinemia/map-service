package tk.laurenfrost.mapservice.Service;

import org.springframework.stereotype.Service;
import tk.laurenfrost.mapservice.Entity.Area;
import tk.laurenfrost.mapservice.Repository.AreaRepository;

import java.util.List;

@Service
public class AreaService {

    private final AreaRepository areaRepository;

    public AreaService(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }


    public List<Area> getAll() {
        return areaRepository.findAll();
    }

    /**
     * @param area incoming area
     * @return saved area id
     */
    public Long addArea(Area area) {
        return areaRepository.save(area).getId();
    }

}
