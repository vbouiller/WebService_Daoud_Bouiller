package fr.emse.majeureinfo.webserviceproject.web;

import fr.emse.majeureinfo.webserviceproject.model.Building;
import fr.emse.majeureinfo.webserviceproject.model.Room;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BuildingDto {

    private final Long id;
    private final String name;
    private final Set<Room> rooms;
    private final int N_rooms;
    private final int N_Onlight;
    private final int N_Onringers;

    public BuildingDto(Building building){
        this.id=building.getId();
        this.rooms=building.getRooms();
        this.name=building.getName();
        this.N_rooms = this.rooms.size();
        this.N_Onlight=building.Num_On_Light();
        this.N_Onringers=building.Num_On_Ringer();
    }

    public Long getId() {
        return id;
    }
    public String getName(){ return name;}
    public int getN_rooms() {
        return N_rooms;
    }
    public int getN_Onlight(){ return N_Onlight;}
    public int getN_Onringers(){return N_Onringers;}

    public List<RoomDto> getRooms() {
        return rooms.stream().map(RoomDto::new).collect(Collectors.toList());
    }


}
