package fr.emse.majeureinfo.webserviceproject.dao;


import fr.emse.majeureinfo.webserviceproject.model.Light;
import fr.emse.majeureinfo.webserviceproject.model.Room;

import java.util.List;

public interface RoomDaoCustom {

    public List<Room> findRoomsWithOnLights();

}


