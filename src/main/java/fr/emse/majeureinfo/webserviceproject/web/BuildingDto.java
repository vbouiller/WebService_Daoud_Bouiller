package fr.emse.majeureinfo.webserviceproject.web;

import fr.emse.majeureinfo.webserviceproject.dao.RoomDao;
import fr.emse.majeureinfo.webserviceproject.model.Building;
import fr.emse.majeureinfo.webserviceproject.model.Light;
import fr.emse.majeureinfo.webserviceproject.model.Noise;
import fr.emse.majeureinfo.webserviceproject.model.Room;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BuildingDto {

    private final Long id;
    private final Set<Room> rooms;

    public BuildingDto(Building building){
        this.id=building.getId();
        this.rooms=building.getRooms();
    }

    public Long getId() {
        return id;
    }

    public List<RoomDto> getRooms() {
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }


}
