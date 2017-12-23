package fr.emse.majeureinfo.webserviceproject.web;

import fr.emse.majeureinfo.webserviceproject.dao.BuildingDao;
import fr.emse.majeureinfo.webserviceproject.model.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/buildings")
@Transactional
public class BuildingController {
    private final BuildingDao buildingDao;

    public BuildingController(BuildingDao buildingDao) {
        this.buildingDao = buildingDao;
    }
    @GetMapping
    public List<BuildingDto> list(){
        return buildingDao.findAll().stream().map(BuildingDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{buildingId}")
    public BuildingDto get(@PathVariable Long buildingId){
        return new BuildingDto(
                buildingDao.findOne(buildingId));
    }
    @GetMapping(value = "/light/{status}")
    public List<RoomDto> listWithLightStatus(@PathVariable Status status){
        return buildingDao.findRoomsByLightStatus(status).stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/ringer/{status}")
    public List<RoomDto> listWithRingerStatus(@PathVariable Status status){
        return buildingDao.findRoomsByNoiseStatus(status).stream().map(RoomDto::new).collect(Collectors.toList());
    }

}
