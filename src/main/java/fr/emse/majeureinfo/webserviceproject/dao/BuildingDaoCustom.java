package fr.emse.majeureinfo.webserviceproject.dao;


import fr.emse.majeureinfo.webserviceproject.model.Building;
import fr.emse.majeureinfo.webserviceproject.model.Room;
import fr.emse.majeureinfo.webserviceproject.model.Status;

import java.util.List;

public interface BuildingDaoCustom {

    public List<Room> countRooms();
    public List<Building> countRoomsWithLightOn();
    public List<Building> countRoomsWithRingerOn();
    public List<Room> findRoomsByLightStatus(Status status,Long id);
    public List<Room> findRoomsByNoiseStatus(Status status, Long id);


}


