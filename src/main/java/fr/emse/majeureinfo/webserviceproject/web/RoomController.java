package fr.emse.majeureinfo.webserviceproject.web;

import fr.emse.majeureinfo.webserviceproject.dao.BuildingDao;
import fr.emse.majeureinfo.webserviceproject.dao.LightDao;
import fr.emse.majeureinfo.webserviceproject.dao.RoomDao;
import fr.emse.majeureinfo.webserviceproject.model.Light;
import fr.emse.majeureinfo.webserviceproject.model.Noise;
import fr.emse.majeureinfo.webserviceproject.model.Room;
import fr.emse.majeureinfo.webserviceproject.model.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import fr.emse.majeureinfo.webserviceproject.mqtt.MqttConnector;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/rooms")
@Transactional
public class RoomController {
    private final RoomDao roomDao;
    private final BuildingDao buildingDao;
    private final LightDao lightDao;
    String topic ="philipshue/";

    public RoomController(RoomDao roomDao, BuildingDao buildingDao,LightDao lightDao){
        this.roomDao=roomDao;
        this.buildingDao = buildingDao;
        this.lightDao =  lightDao;
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
        RestTemplate restTemplate = new RestTemplate();
        Light light = roomDao.findOne(roomId).getLight();

        String currenttopic =topic+light.getId();

        if(light.getStatus().equals(Status.OFF))
        {
            light.setStatus(Status.ON);
            MqttConnector.publish(currenttopic,"on");
        }
        else
        {
            light.setStatus(Status.OFF);
            MqttConnector.publish(currenttopic,"off");
        }

        return list();
    }
    @PostMapping(path = "/switch/lights/{status}")
    public List<RoomDto> switchAllLight(@PathVariable Status status){
        Status newSt=Status.ON;
        if(status.equals(Status.ON))newSt=Status.OFF;
        List<Room> toChange=roomDao.findRoomsByLightStatus(status);
        for (Room room:toChange) {
            room.getLight().setStatus(newSt);
            MqttConnector.publish(topic+room.getLight().getId(),newSt.toString().toLowerCase());

        }
        //RestTemplate restTemplate = new RestTemplate();
        //lightDao.turnAllLights(status);
        return list();
    }
    @PostMapping(path = "/switch/ringers/{status}")
    public List<RoomDto> switchAllRingers(@PathVariable Status status){
        Status newSt=Status.ON;
        if(status.equals(Status.ON))newSt=Status.OFF;
        List<Room> toChange=roomDao.findRoomsByRingerStatus(status);
        for (Room room:toChange) {
            room.getNoise().setStatus(newSt);
        }
        //RestTemplate restTemplate = new RestTemplate();
        //lightDao.turnAllLights(status);
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
    @GetMapping(value ="/building/{Bid}")
    public List<RoomDto> listByBuilding(@PathVariable Long Bid){
        return  buildingDao.findOne(Bid).getRooms().stream().map(RoomDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/PHueRefresh")
    public void getPhilipsHueData(@RequestBody JSONArray pHueLights){
        int arrayLength = pHueLights.length();
        int count = 0;

        try {
            while (count < arrayLength) {
                JSONObject light = pHueLights.getJSONObject(count);
                lightDao.updateLights(light.getInt("id"),light.getInt("level"), light.getBoolean("status") );
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
