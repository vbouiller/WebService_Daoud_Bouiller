package fr.emse.majeureinfo.webserviceproject.web;

import fr.emse.majeureinfo.webserviceproject.dao.RoomDao;
import fr.emse.majeureinfo.webserviceproject.dao.RoomDaoImpl;
import fr.emse.majeureinfo.webserviceproject.model.Light;
import fr.emse.majeureinfo.webserviceproject.model.Noise;
import fr.emse.majeureinfo.webserviceproject.model.Status;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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

    @PostMapping(path = "/switch/light", consumes = "application/json")
    public RoomDto switchLight(@RequestBody String roomIdString){

        Long roomId = Long.parseLong(roomIdString);
        Light light = roomDao.findOne(roomId).getLight();
        if(light.getStatus().equals(Status.OFF))
            light.setStatus(Status.ON);
        else
            light.setStatus(Status.OFF);

        return get(roomId);
    }

    @PostMapping(path = "/switch/ringer", consumes = "application/json")
    public RoomDto switchRinger(@RequestBody String roomIdString){

        Long roomId = Long.parseLong(roomIdString);
        Noise noise = roomDao.findOne(roomId).getNoise();
        if(noise.getStatus().equals(Status.OFF))
            noise.setStatus(Status.ON);
        else
            noise.setStatus(Status.OFF);

        return get(roomId);
    }

    @GetMapping(value = "/light/on")
    public List<RoomDto> listWithOnLight(){
        return roomDao.findRoomsWithOnLights().stream().map(RoomDto::new).collect(Collectors.toList());
    }


}
