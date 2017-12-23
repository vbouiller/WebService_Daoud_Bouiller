package fr.emse.majeureinfo.webserviceproject.dao;


import fr.emse.majeureinfo.webserviceproject.model.Room;
import fr.emse.majeureinfo.webserviceproject.model.Status;

import java.util.List;

public interface BuildingDaoCustom {

    public List<Room> countRooms();
    public List<Room> countRoomsWithLightOn();
    public List<Room> countRoomsWithRingerOn();
    public List<Room> findRoomsByLightStatus(Status status);
    public List<Room> findRoomsByNoiseStatus(Status status);


}


