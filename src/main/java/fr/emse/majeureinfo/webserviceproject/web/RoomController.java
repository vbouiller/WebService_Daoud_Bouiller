package fr.emse.majeureinfo.webserviceproject.web;

import fr.emse.majeureinfo.webserviceproject.dao.RoomDao;
import fr.emse.majeureinfo.webserviceproject.model.Light;
import fr.emse.majeureinfo.webserviceproject.model.Noise;
import fr.emse.majeureinfo.webserviceproject.model.Status;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/rooms")
@Transactional
public class RoomController {
    private final RoomDao roomDao;

    public RoomController(RoomDao roomDao){
        this.roomDao=roomDao;
    }

    @GetMapping
    public List<RoomDto> list(){
        return roomDao.findAll().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/{roomId}")
    public RoomDto get(@PathVariable Long roomId){
        return new RoomDto(roomDao.findOne(roomId));
    }

    @PostMapping(path = "/{roomId}/switch/light")
    public List<RoomDto> switchLight(@PathVariable Long roomId){

        Light light = roomDao.findOne(roomId).getLight();
        if(light.getStatus().equals(Status.OFF))
            light.setStatus(Status.ON);
        else
            light.setStatus(Status.OFF);

        return list();
    }

    @PostMapping(path = "/{roomId}/switch/ringer")
    public List<RoomDto> switchRinger(@PathVariable Long roomId){

        Noise noise = roomDao.findOne(roomId).getNoise();
        if(noise.getStatus().equals(Status.OFF))
            noise.setStatus(Status.ON);
        else
            noise.setStatus(Status.OFF);

        return list();
    }

    @GetMapping(value = "/light/{status}")
    public List<RoomDto> listWithLightStatus(@PathVariable Status status){
        return roomDao.findRoomsByLightStatus(status).stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/ringer/{status}")
    public List<RoomDto> listWithRingerStatus(@PathVariable Status status){
        return roomDao.findRoomsByRingerStatus(status).stream().map(RoomDto::new).collect(Collectors.toList());
    }


}
