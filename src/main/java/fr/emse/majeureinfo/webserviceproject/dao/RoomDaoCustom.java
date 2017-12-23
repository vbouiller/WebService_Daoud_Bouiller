package fr.emse.majeureinfo.webserviceproject.dao;


import fr.emse.majeureinfo.webserviceproject.model.Room;
import fr.emse.majeureinfo.webserviceproject.model.Status;

import java.util.List;

public interface RoomDaoCustom {

    public List<Room> findRoomsWithOnLights();
    public List<Room> findRoomsByLightStatus(Status st);
    public List<Room> findRoomsByRingerStatus(Status st);

}


